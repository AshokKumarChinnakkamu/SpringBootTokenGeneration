package com.example.demo.Service;

import com.example.demo.Model.UserPrincipal;
import com.example.demo.Model.stud_table;
import com.example.demo.Repository.StudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements UserDetailsService {
    @Autowired
    private StudRepo repo;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;
//    public Object getAll() {
//       return repo.findAll();
//    }

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(10);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        stud_table user=repo.findByUsername(username);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }

    public stud_table storeUser(stud_table studTable) {
        studTable.setPassword(encoder.encode(studTable.getPassword()));
        return repo.save(studTable);
    }

    public List<stud_table> getStudents() {
        return repo.findAll();
    }


    public String verify(stud_table studTable) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(studTable.getUsername(),studTable.getPassword()));
        if(authentication.isAuthenticated()){
            return JWTgenerater.generateToken(studTable.getUsername());
        }
        return "Login Failed";
    }
}

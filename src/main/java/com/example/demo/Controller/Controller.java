package com.example.demo.Controller;

import com.example.demo.Model.StudentData;
import com.example.demo.Model.stud_table;
import com.example.demo.Service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
StudentService service;
//    @GetMapping("/session")
//    public String greet(HttpServletRequest request){
//        return "Session ID "+request.getSession().getId();
//    }
//
//    List<StudentData> studentData=new ArrayList<>(List.of(new StudentData(1,"Ashok",60) ));
//    @GetMapping("/students")
//    public List<StudentData> getall(){
//        return studentData;
//    }
//
//    @GetMapping("/")
//    public CsrfToken getCsrf(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }
//
//    @PostMapping("/students")
//    public StudentData addStudent(@RequestBody StudentData student){
//        studentData.add(student);
//        return student;
//    }


    @PostMapping("/register")
    public ResponseEntity<stud_table> storeUser(@RequestBody stud_table studTable){
            return new ResponseEntity<>(service.storeUser(studTable),HttpStatus.OK);
    }
    @GetMapping("/students")
    public ResponseEntity<List<stud_table>> getStudents(){
        return new ResponseEntity<>(service.getStudents(),HttpStatus.FOUND);
    }

    @PostMapping("/login")
        public String loginMethod(@RequestBody stud_table studTable){

         return service.verify(studTable);
        }
}

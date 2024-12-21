package com.example.demo.Model;

public class StudentData {
    private int id;
    private String Name;
    private int marks;

    public StudentData(int id, String name, int marks) {
        this.id = id;
        Name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}

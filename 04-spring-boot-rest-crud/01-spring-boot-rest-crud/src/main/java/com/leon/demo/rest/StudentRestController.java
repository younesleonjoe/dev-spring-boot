package com.leon.demo.rest;

import com.leon.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api")
@RestController
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void loadData() {
        this.students = new ArrayList<>();
        this.students.add(new Student("Leon", "Younes"));
        this.students.add(new Student("Guitta", "Saade"));
        this.students.add(new Student("Charbel", "Saade"));
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // We can implement it like this or wrap it in try catch
        // if (studentId < 0 || studentId >= students.size())
        // throw new StudentNotFoundException("Student id not found - " + studentId);
        // return students.get(studentId);

        try {
            return students.get(studentId);
        } catch (Exception exception) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
    }
}

package com.example.StudentManagementPostgre.service;

import com.example.StudentManagementPostgre.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    ResponseEntity<List<Student>> getAllStudents();
    ResponseEntity<Student> getStudentById(Long id);
    Student createStudent(Student student);
    Student updateStudent(Long id, Student student);
    ResponseEntity<Student> deleteStudent(Long id);
}

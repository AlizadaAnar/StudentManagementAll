package com.example.StudentManagementPostgre.service.impl;

import com.example.StudentManagementPostgre.entity.Student;
import com.example.StudentManagementPostgre.repository.StudentRepository;
import com.example.StudentManagementPostgre.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> studentList = new ArrayList<>(studentRepository.findAll());
            if (studentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Student> getStudentById(Long id) {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    public Student createStudent(Student student) {
        Student stud = convertToEntity(student);
        Student savedStudent = studentRepository.save(stud);
        return convertToEntity(savedStudent);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAge(student.getAge());

        Student updatedStudent = studentRepository.save(existingStudent);
        return convertToEntity(updatedStudent);
    }

    @Override
    public ResponseEntity<Student> deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Student convertToEntity(Student student) {
        return Student.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .age(student.getAge())
                .build();
    }
}

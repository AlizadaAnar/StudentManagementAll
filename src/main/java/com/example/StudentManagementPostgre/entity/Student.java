package com.example.StudentManagementPostgre.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Students")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int age;


}

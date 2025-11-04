package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    @Column(name = "name", length = 100)
    private String name;

    private boolean active = true;

    @Lob
    private String about;

}

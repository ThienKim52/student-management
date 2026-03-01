package vn.edu.hcmut.cse.adse.lab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private String id; // Sử dụng String (MSSV hoặc UUID), không dùng @GeneratedValue
    private String name;
    private String email;
    private int age;

    // Default Constructor (Bắt buộc phải có đối với JPA/Hibernate)
    public Student() {
    }

    // Constructor có tham số
    public Student(String id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // --- Getters & Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
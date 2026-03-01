package vn.edu.hcmut.cse.adse.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public void save(Student student) {
        // Nếu ID chưa có (khi thêm mới), tạo UUID ngẫu nhiên
        if (student.getId() == null || student.getId().isEmpty()) {
            student.setId(UUID.randomUUID().toString());
        }
        repository.save(student);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
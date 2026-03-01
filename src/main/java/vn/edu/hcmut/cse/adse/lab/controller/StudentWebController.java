package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    // 1. Trang Danh Sách (List View)
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students = (keyword != null && !keyword.isEmpty()) 
                ? service.searchByName(keyword) 
                : service.getAll();
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    // 2. Trang Chi Tiết (Detail View)
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "student_detail";
    }

    // 3. Hiển thị Form Thêm Mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student()); // Gửi đối tượng rỗng
        return "student_form";
    }

    // 4. Hiển thị Form Chỉnh Sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student); // Gửi đối tượng có sẵn dữ liệu
        return "student_form";
    }

    // 5. Xử lý Lưu dữ liệu (Cho cả Thêm Mới và Sửa)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/students"; // Điều hướng về Trang Danh Sách
    }

    // 6. Xử lý Xóa
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/students"; // Điều hướng về Trang Danh Sách
    }
}
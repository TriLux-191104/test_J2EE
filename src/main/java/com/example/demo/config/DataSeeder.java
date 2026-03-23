package com.example.demo.config;

import com.example.demo.model.Category;
import com.example.demo.model.Course;
import com.example.demo.model.Role;
import com.example.demo.model.Student;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Tạo Roles (ADMIN, STUDENT) nếu chưa có
        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole == null) {
            adminRole = new Role(null, "ADMIN");
            roleRepository.save(adminRole);
        }

        Role studentRole = roleRepository.findByName("STUDENT");
        if (studentRole == null) {
            studentRole = new Role(null, "STUDENT");
            roleRepository.save(studentRole);
        }

        // 2. Tạo tài khoản ADMIN mặc định (Username: admin, Password: 123)
        if (studentRepository.findByUsername("admin") == null) {
            Student admin = new Student();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123")); // Mật khẩu là 123
            admin.setEmail("admin@gmail.com");
            admin.setRoles(Set.of(adminRole));
            studentRepository.save(admin);
        }

        // 3. Tạo tài khoản STUDENT mẫu (Username: student, Password: 123)
        if (studentRepository.findByUsername("student") == null) {
            Student student = new Student();
            student.setUsername("student");
            student.setPassword(passwordEncoder.encode("123"));
            student.setEmail("student@gmail.com");
            student.setRoles(Set.of(studentRole));
            studentRepository.save(student);
        }
    }
}
package com.example.sms.controller;

import com.example.sms.entity.Course;
import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
//import javax.validation.Valid; @Valid
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/admin-dashboard/add-student")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/AddStudent"; // Return the add-student form
    }

    @PostMapping("/admin-dashboard/add-student")
    public String addStudent( @ModelAttribute("student") Student student, BindingResult result) {
//        if (result.hasErrors()) {
//            return "add-student"; // Return the form with validation errors
//        }

        
        Student savedStudent = studentService.registerStudent(student);

        if (savedStudent == null) {
            // Handle if the student was not saved successfully (optional)
            return "error-page"; // Redirect to an error page
        }

        return "redirect:/admin-dashboard"; // Redirect to the dashboard after successful addition
    }

    @GetMapping("/admin-dashboard/remove-student")
    public String showRemoveStudentForm(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "remove-student"; // Return the remove-student form
    }

    // Add logic for removing a student

    @GetMapping("/admin-dashboard/search-student")
    public String showSearchStudentForm() {
        return "students/SearchStudent"; // Return the search-student form
    }

//    @PostMapping("/admin-dashoard/search-student")
//    public String searchStudent(@RequestParam("searchIdentifier") String searchIdentifier, Model model) {
//        List<Student> searchResults = studentService.searchStudentsByIdOrName(searchIdentifier);
//        model.addAttribute("searchResults", searchResults);
//        return "search-results"; // Return the search-results view
//    }
    
    @GetMapping("/admin-dashboard/search-student/")
    public String searchStudent(@RequestParam(value = "query", required = false) String query,
                                @RequestParam(value = "criteria", required = false) String criteria,
                                Model model) {
        List<Student> students = new ArrayList<>();

        if (criteria != null && query != null) {
            if (criteria.equals("studentId")) {
                students = studentService.searchStudentByStudentId(query);
            } else if (criteria.equals("firstName")) {
                students = studentService.searchStudentByName(query);
            }
        }

        model.addAttribute("students", students);
        return "students/SearchStudent"; // Return the name of the Thymeleaf template
    }

    @GetMapping("/admin-dashboard/display-students")
    public String displayAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "display-students"; // Return the display-students view
    }
}


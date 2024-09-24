package com.example.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.sms.entity.Course;
import com.example.sms.service.CourseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Controller
public class CourseController {
	
	@Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/admin-dashboard/all-courses")
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses/AllCourses";
    }
    
    @GetMapping("/admin-dashboard/add-courses")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/AddCourse"; // return the name of your HTML file for adding a course
    }

    @PostMapping("/admin-dashboard/add-courses")
    public String addCourse(@ModelAttribute("course") Course course) {
        // Save the course using the CourseService
        courseService.createCourse(course);
        // Redirect to the page displaying all courses
        return "redirect:/admin-dashboard"; // Assuming you have a mapping for displaying all courses
    }
    
    @DeleteMapping("/admin-dashboard/remove/{course_id}")
    public ResponseEntity<String> removeCourseFromStudent(@PathVariable("course_id") String courseId) {
        try {
            courseService.deleteCourseByCourseId(courseId);
            return ResponseEntity.ok("Course removed from student successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to remove course from student: " + e.getMessage());
        }
    }
    
    @GetMapping("/admin-dashboard/search-course")
    public String searchPage() {
        return "courses/SearchCourse"; // Return the name of your Thymeleaf template
    }
    
    
    @GetMapping("/admin-dashboard/search-course/")
    public String searchCourses(@RequestParam(value = "query", required = false) String query,
                                @RequestParam(value = "criteria", required = false) String criteria,
                                Model model) {
        List<Course> courses = new ArrayList<>();

        if (criteria != null && query != null) {
            if (criteria.equals("course_name")) {
                courses = courseService.searchCourseByName(query);
            } else if (criteria.equals("course_id")) {
                courses = courseService.searchCourseByCourseId(query);
            }
        }

        model.addAttribute("courses", courses);
        return "courses/SearchCourse"; // Return the name of the Thymeleaf template
    }
    
    @GetMapping("/admin-dashboard/confirm-remove/{courseId}")
    public String confirmRemoveCourse(@PathVariable("courseId") String courseId, Model model) {
        Course course = courseService.getCourseByCourseId(courseId);
        model.addAttribute("course", course);
        return "courses/RemoveCourse"; // Return the name of your confirmation page HTML file
    }

    @PostMapping("/admin-dashboard/remove/{courseId}")
    public String removeCourse(@PathVariable("courseId") String courseId) {
        courseService.deleteCourseByCourseId(courseId);
        return "redirect:/admin-dashboard/all-courses"; // Redirect to the list of courses page after removal
    }
}


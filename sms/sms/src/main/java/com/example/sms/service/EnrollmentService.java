package com.example.sms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sms.entity.Course;
import com.example.sms.entity.Student;
import com.example.sms.repository.CourseRepository;
import com.example.sms.repository.StudentRepository;

@Service
public class EnrollmentService {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Method to add a student to a course
    public void enrollStudentToCourse(Long studentId, Long courseId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Student student = studentOptional.get();
            Course course = courseOptional.get();
            
            // Add course to student
            student.getCourses().add(course);
            studentRepository.save(student);

            // Add student to course
            course.getStudents().add(student);
            courseRepository.save(course);
        } else {
            // Handle if student or course is not found
            throw new RuntimeException("Student or course not found");
        }
    }

	

    // Method to remove a student from a course
//    public void removeStudentFromCourse(Long studentId, Long courseId) {
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        Optional<Course> courseOptional = courseRepository.findById(courseId);
//
//        if (studentOptional.isPresent() && courseOptional.isPresent()) {
//            Student student = studentOptional.get();
//            Course course = courseOptional.get();
//            
//            // Remove course from student
//            student.getCourses().remove(course);
//            studentRepository.save(student);
//
//            // Remove student from course
//            course.getStudents().remove(student);
//            courseRepository.save(course);
//        } else {
//            // Handle if student or course is not found
//            throw new RuntimeException("Student or course not found");
//        }
//    }
}
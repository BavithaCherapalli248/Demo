package com.example.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sms.entity.Course;
import com.example.sms.entity.Student;
//import com.example.sms.entity.Student;
import com.example.sms.repository.CourseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course createCourse(Course course) {
        // You may generate the course ID here
        // For demonstration, let's assume the course ID is generated elsewhere and set in the course object
        return courseRepository.save(course);
    }

//    public Course updateCourse(Long id, Course course) {
//        Course existingCourse = courseRepository.findById(id).orElse(null);
//        if (existingCourse != null) {
//            // Update existing course properties with the new values
//            existingCourse.setCourseName(course.getCourseName());
//            existingCourse.setDescription(course.getDescription());
//            // You may handle other properties update here
//            return courseRepository.save(existingCourse);
//        } else {
//            return null; // Course not found
//        }
//    }
//
    public boolean deleteCourseByCourseId(String courseId) {
        Course existingCourse = courseRepository.findByCourseId(courseId);
        if (existingCourse != null) {
            courseRepository.delete(existingCourse);
            return true;
        } else {
            return false; // Course not found
        }
    }
    
    public List<Course> searchCourseByName(String nameQuery) {
        return courseRepository.findByCourseNameContainingIgnoreCase(nameQuery);
    }

    public List<Course> searchCourseByCourseId(String courseIdQuery) {
        return courseRepository.findByCourseIdContainingIgnoreCase(courseIdQuery);
    }
    public Course getCourseByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }
//    
//    public Set<Student> getStudentsEnrolledInCourse(Long courseId) {
//        Course course = courseRepository.findById(courseId).orElse(null);
//        if (course != null) {
//            return course.getStudents();
//        }
//        return new HashSet<>(); // Return an empty set if course not found or no students enrolled
//    }
//    
//    public CourseService(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }
//
//    public List<Course> getCoursesByStudentId(String studentId) {
//        // Implement logic to retrieve courses by studentId from the repository
//        // Assuming you have a ManyToMany relationship between Student and Course,
//        // you can use a JPQL query to fetch courses by studentId
//        return courseRepository.findByStudents_StudentId(studentId);
//    }
    
    public Set<Student> getEnrolledStudentsByCourseId(Long id){
    	Course course= courseRepository.findById(id).orElse(null);
    	if(course != null) {
    		return course.getStudents();
    	}
    	return new HashSet<>();
    }
    
    public void dropStudentFromCourse(String studentId, String courseId) {
    	Course course= courseRepository.findByCourseId(courseId);
    	
    	if(course != null ) {
    		Set<Student> students= course.getStudents();
    		students.removeIf(student -> student.getStudentId().equals(studentId));
    		course.setStudents(students);
    		courseRepository.save(course);
    	}
    }
}


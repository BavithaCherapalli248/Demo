package com.example.sms.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sms.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // You can define additional query methods here if needed
	 Course findByCourseId(String courseId);
	 List<Course> findByCourseNameContainingIgnoreCase(String nameQuery);
	    List<Course> findByCourseIdContainingIgnoreCase(String courseIdQuery);
//		Course findByCourseIdClass(Course courseId);
	    
//	    List<Course> findByStudents_StudentId(String studentId);
}

package com.example.sms.repository;


import com.example.sms.entity.Course;
import com.example.sms.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findById(Long id);

    // Method to find students by student ID
    Student findByStudentId(String studentId);

    // Custom query method to search students by student ID or first name or last name
    List<Student> findByStudentIdOrFirstNameOrLastName(String studentId, String firstName, String lastName);
    
    @Query("SELECT s FROM Student s WHERE s.studentId = :identifier OR s.firstName = :identifier OR s.lastName = :identifier")
    List<Student> searchStudentsByIdOrName(@Param("identifier") String identifier);
    
//    List<Student> findByCourses_CourseId(String courseId);
    
//    Student findByStudentIdClass(Student studentId);
    
    List<Student> findByFirstNameContainingIgnoreCase(String firstNameQuery);
    List<Student> findByStudentIdContainingIgnoreCase(String studentIdQuery);
    
    @Query("SELECT s.enrolledCourses FROM Student s WHERE s.id=:id")
    Set<Course> findEnrolledCoursesByStudent(@Param("id") Long id);
    
}


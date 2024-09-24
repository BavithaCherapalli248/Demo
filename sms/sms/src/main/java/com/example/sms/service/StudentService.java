package com.example.sms.service;

import com.example.sms.entity.Course;
import com.example.sms.entity.Student;
import com.example.sms.repository.CourseRepository;
import com.example.sms.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    
    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    // Method to register a new student
    public Student registerStudent(Student student) {
        // Save the student to the database
//    	if (student.getDateOfBirth() == null) {
//            // Handle the case where dateOfBirth is not initialized
//            throw new IllegalArgumentException("Date of birth must be provided.");
//        }
    	
        return studentRepository.save(student);
    }

    // Method to retrieve all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Method to retrieve a student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Method to retrieve a student by student ID
    public Student getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }
    
    public List<Student> searchStudentByName(String firstNameQuery) {
        return studentRepository.findByFirstNameContainingIgnoreCase(firstNameQuery);
    }

    public List<Student> searchStudentByStudentId(String studentIdQuery) {
        return studentRepository.findByStudentIdContainingIgnoreCase(studentIdQuery);
    }
    

//    public List<Student> searchStudentsByIdOrName(String identifier) {
//        return studentRepository.searchStudentsByIdOrName(identifier);
//    }

//    // Method to update a student
//    public Student updateStudent(Student updatedStudent) {
//        // Check if the student exists in the database
//        if (studentRepository.existsById(updatedStudent.getId())) {
//            // Save the updated student to the database
//            return studentRepository.save(updatedStudent);
//        } else {
//            // If the student does not exist, return null or throw an exception
//            // depending on your application's requirements
//            return null;
//        }
//    }

    // Method to delete a student by ID
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
    
    public void enrollStudentInCourse(Long studentId, String courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findByCourseId(courseId);
        if (student != null && course != null) {
            student.getCourses().add(course);
            studentRepository.save(student);
        }
    }

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
    
//    public void dropStudentFromCourse(Long studentId, Long courseId) {
//        Student student = studentRepository.findById(studentId).orElse(null);
//        Course course = courseRepository.findById(courseId).orElse(null);
//        if (student != null && course != null) {
//            student.getCourses().remove(course);
//            studentRepository.save(student);
//        }
//    }
    
//    public Set<Course> getCoursesEnrolledByStudent(Long studentId) {
//        Student student = studentRepository.findById(studentId).orElse(null);
//        if (student != null) {
//            return student.getCourses();
//        }
//        return new HashSet<>(); // Return an empty set if student not found or not enrolled in any courses
//    }
    
    

//    public List<Student> getStudentsByCourseId(String courseId) {
//        // Implement logic to retrieve students by courseId from the repository
//        // Assuming you have a ManyToMany relationship between Student and Course,
//        // you can use a JPQL query to fetch students by courseId
//        return studentRepository.findByCourses_CourseId(courseId);
//    }
	
	public Set<Course> getEnrolledCoursesByStudentId(Long id){
//		Student student = studentRepository.findById(studentId).orElse(null);
//		if(student!=null) {
//			return student.getCourses();
//		}
//		return new HashSet<>();
		return studentRepository.findEnrolledCoursesByStudent(id);
	}
}


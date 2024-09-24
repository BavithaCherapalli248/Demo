package com.example.sms.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sms.entity.Course;
import com.example.sms.entity.Student;
import com.example.sms.service.CourseService;
import com.example.sms.service.EnrollmentService;
import com.example.sms.service.StudentService;

@Controller
@RequestMapping("/admin-dashboard")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/enroll-student")
	public String showEnrollmentForm(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		model.addAttribute("courses", courseService.getAllCourses());
		
		return "enrollment/EnrollStudent";
	}
	
	@PostMapping("/enroll-student/")
	public String enrollStudentInCourses(@RequestParam("studentId") String studentId,
										 @RequestParam("courseId") String courseId) {
		Student student = studentService.getStudentByStudentId(studentId);
		Course course= courseService.getCourseByCourseId(courseId);
		System.out.println(student+" "+course);
		if(student!=null && course!=null) {
			student.getCourses().add(course);
			studentService.saveStudent(student);
			return "redirect:/admin-dashboard";
		}else {
			return "redirect:/admin-dashboard/enroll-student?error";
		}
//		return "redirect:/admin-dashboard";
	}
	
	@GetMapping("/enrolled-students")
	public String showEnrolledCoursesPage( Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "enrollment/EnrolledStudents";
	}
	
	@GetMapping("/enrolled-students/")
	public String enrolledCoursesByStudent(@RequestParam("studentId") String studentId,
										Model model) {
		Student student = studentService.getStudentByStudentId(studentId);
		
		
		if(student!=null) {
			model.addAttribute("students",student);
			
			Set<Course> enrolledCourses=student.getCourses();
			model.addAttribute("courses",enrolledCourses);
			return "enrollment/EnrolledStudents";
		}else {
			return "enrollment/EnrolledStudents?error";
		}
//		return "redirect:/admin-dashboard";
	}
	
	@GetMapping("/enrolled-courses")
	public String showEnrolledStudentsPage( Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "enrollment/EnrolledCourses";
	}
	
	@GetMapping("/enrolled-courses/")
	public String enrolledStudentsByCourses(@RequestParam("courseId") String courseId,
										Model model) 
	{
		Course course = courseService.getCourseByCourseId(courseId);
		
		
		if(course!=null) {
			model.addAttribute("courses",course);
			
			Set<Student> enrolledStudents=course.getStudents();
			model.addAttribute("students",enrolledStudents);
			return "enrollment/EnrolledCourses";
		}else {
			return "enrollment/EnrolledCourses?error";
		}
//		return "redirect:/admin-dashboard";
	}
	
	@GetMapping("/drop-student-from-course/")
	public String dropStudentFromCourse(@RequestParam("studentId") String studentId,
										@RequestParam("courseId") String courseId) {
		courseService.dropStudentFromCourse(studentId, courseId);
		
		return "redirect:/admin-dashboard";
	}
	
	
}

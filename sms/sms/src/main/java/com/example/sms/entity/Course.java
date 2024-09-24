package com.example.sms.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Sequential ID

    @Column(name = "course_id", nullable = false, unique = true)
    public String courseId; // Random 5-letter ID

    @Column(name = "course_name", nullable = false)
    public String courseName;

    @Column(name = "description")
    public String description;

    @ManyToMany(mappedBy="enrolledCourses")
    private Set<Student> enrolledStudents = new HashSet<>();
    // Constructors

    public Course() {
        // Default constructor
    	this.courseId = generateRandomId();
    }

    public Course(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
        
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Student> getStudents() {
        return enrolledStudents;
    }

    public void setStudents(Set<Student> students) {
        this.enrolledStudents = students;
    }

    // Generate random 5-letter text
    public String generateRandomId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = (int) (characters.length() * Math.random());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    // toString method

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


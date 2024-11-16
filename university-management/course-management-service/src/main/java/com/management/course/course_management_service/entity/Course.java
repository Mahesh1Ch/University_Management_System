package com.management.course.course_management_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "courses")
public class Course
{

    @Id
    private String courseId;

    @NotBlank(message = "Course name cannot be blank")
    @Column(nullable = false)
    private String courseName;

    @NotBlank(message = "Description cannot be blank")
    @Column(nullable = false)
    private String description;



    public Course() {}

    public Course(String courseId, String courseName, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
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
}

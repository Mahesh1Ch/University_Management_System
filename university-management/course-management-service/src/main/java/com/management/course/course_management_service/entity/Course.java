package com.management.course.course_management_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String course_id;

    @NotBlank(message = "Course name cannot be blank")
    @Column(nullable = false)
    private String course_name;

    @NotBlank(message = "Description cannot be blank")
    @Column(nullable = false)
    private String description;


    public Course() {}

    public Course(String course_id, String course_name, String description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.description = description;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public @NotBlank(message = "Course name cannot be blank") String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(@NotBlank(message = "Course name cannot be blank") String course_name) {
        this.course_name = course_name;
    }

    public @NotBlank(message = "Description cannot be blank") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description cannot be blank") String description) {
        this.description = description;
    }
}


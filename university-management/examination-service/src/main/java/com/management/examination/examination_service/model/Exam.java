package com.management.examination.examination_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "exams") // Specify the table name in the database
public class Exam {

    @Id
    private String id;

    @NotBlank(message = "Exam name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Score cannot be null")
    @Min(value = 0, message = "Score must be at least 0")
    @Column(nullable = false)
    private Double score;

    @NotNull(message = "Course ID cannot be null")
    @Column(nullable = false)
    private String courseId; // Assuming you want to link the exam to a course

    public Exam() {
    }

    public Exam(String id,String name, String description, Double score, String courseId) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.score = score;
        this.courseId = courseId;
    }

    // Getters and setters...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String  getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
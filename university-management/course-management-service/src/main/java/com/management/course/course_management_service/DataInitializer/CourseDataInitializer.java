package com.management.course.course_management_service.DataInitializer;
import com.management.course.course_management_service.entity.Course;
import com.management.course.course_management_service.repository.CourseRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CourseDataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public CourseDataInitializer(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        // Predefined courses
        Course cse = new Course("CSE1001", "Computer Science Engineering", "Focuses on computing technology, algorithms, and software design.");
        Course ece = new Course("ECE1001", "Electronics and Communication Engineering", "Involves the study of electronic devices, circuits, and communication systems.");
        Course civil = new Course("CIV1001", "Civil Engineering", "Concerns the design and construction of infrastructure projects like roads and bridges.");
        Course mech = new Course("ME1001", "Mechanical Engineering", "Involves the study of mechanics, thermodynamics, and materials science.");
        Course chemical = new Course("CHE1001", "Chemical Engineering", "Focuses on the processes involved in the production and use of chemicals.");
        Course it = new Course("IT1001", "Information Technology", "Covers the management and use of computer systems and networks.");
        Course bio = new Course("BME1001", "Biomedical Engineering", "Combines engineering principles with medical and biological sciences.");
        Course architecture = new Course("ARC1001", "Architecture", "The art and science of designing buildings and other physical structures.");
        Course dataScience = new Course("DS1001", "Data Science", "Involves techniques and theories from statistics, machine learning, and data analysis.");
        Course business = new Course("BUS1001", "Business Administration", "Covers the fundamentals of managing a business, including finance, marketing, and operations.");
        Course education = new Course("EDU1001", "Education", "Focuses on teaching methods, educational theory, and curriculum development.");

        // Save predefined courses to the database
        courseRepository.saveAll(Arrays.asList(cse, ece, civil, mech, chemical, it, bio, architecture, dataScience, business, education));
    }

}

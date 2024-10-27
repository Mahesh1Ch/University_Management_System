package com.management.course.course_management_service.service;
import com.management.course.course_management_service.entity.Course;
import com.management.course.course_management_service.exception.ResourceNotFoundException;
import com.management.course.course_management_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {


    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    public Course getCourseById(String courseId)
    {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(String courseId, Course courseDetails) {
        Course course = getCourseById(courseId);
        course.setCourseName(courseDetails.getCourseName());
        course.setDescription(courseDetails.getDescription());
        return courseRepository.save(course);
    }

    public void deleteCourse(String courseId) {
        Course course = getCourseById(courseId);
        courseRepository.delete(course);
    }
}

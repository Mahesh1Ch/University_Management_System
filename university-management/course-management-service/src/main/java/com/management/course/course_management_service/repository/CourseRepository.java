package com.management.course.course_management_service.repository;

import com.management.course.course_management_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,String>
{

}

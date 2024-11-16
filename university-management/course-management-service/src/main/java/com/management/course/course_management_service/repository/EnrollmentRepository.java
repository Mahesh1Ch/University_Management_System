package com.management.course.course_management_service.repository;

import com.management.course.course_management_service.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer>
{
    List<Enrollment> findByCourseId(String courseId);
}

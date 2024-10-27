package com.management.course.course_management_service.service;

import com.management.course.course_management_service.dto.StudentDTO;
import com.management.course.course_management_service.entity.Enrollment;
import com.management.course.course_management_service.repository.CourseRepository;
import com.management.course.course_management_service.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService
{

    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;



    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository,CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Autowired
    private RestTemplate restTemplate;

    public List<StudentDTO> getStudentsByCourseId(String courseId)
    {
        // Fetch all enrollments for the given course ID
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        // Collect the student IDs from enrollments
        List<Integer> studentIds = enrollments.stream()
                .map(Enrollment::getStudentId)
                .toList();

        // Fetch student details from the student service
        List<StudentDTO> studentDetails = new ArrayList<>();
        for (Integer studentId : studentIds)
        {
            StudentDTO student = getStudentFromService(studentId);
                    // restTemplate.getForObject("http://localhost:8080/students/" + studentId, StudentDTO.class);
            studentDetails.add(student);
        }
        return studentDetails;
    }

    @Transactional
    public Enrollment enrollCourseByStudent(int studentId, String courseId)
    {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(courseId);
        enrollment.setStudentId(studentId);
        return enrollmentRepository.save(enrollment);
    }

    public boolean deleteCourseByEnrollId(int enroll_Id)
    {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enroll_Id);
        if(enrollment.isPresent())
        {
            enrollmentRepository.deleteById(enroll_Id);
            return  true;
        }
        return false;
    }

    public Enrollment findEnrollment(int enroll_id)
    {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enroll_id);
        return optionalEnrollment.orElse(null);
    }


    public StudentDTO getStudentFromService(int studentId) {
        String url = "http://localhost:8080/students/" + studentId; // Adjust the base URL if needed
        return restTemplate.getForObject(url, StudentDTO.class);
    }



}

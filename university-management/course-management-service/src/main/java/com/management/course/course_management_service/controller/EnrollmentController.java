package com.management.course.course_management_service.controller;

import com.management.course.course_management_service.dto.StudentDTO;
import com.management.course.course_management_service.entity.Enrollment;
import com.management.course.course_management_service.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("{courseId}")
    public ResponseEntity<List<StudentDTO>> getStudentByCourseId(@PathVariable String courseId)
    {
        List<StudentDTO> students = enrollmentService.getStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Enrollment> enrollCourseByStudent(@RequestParam int studentId, @RequestParam String courseId) {
        Enrollment enrollment = enrollmentService.enrollCourseByStudent(studentId, courseId);
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{enrollId}")
    public ResponseEntity<Void> deleteCourseByEnrollId(@PathVariable int enrollId) {
        boolean isDeleted = enrollmentService.deleteCourseByEnrollId(enrollId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}

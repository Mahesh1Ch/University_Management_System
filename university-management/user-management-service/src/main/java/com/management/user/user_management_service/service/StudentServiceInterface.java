package com.management.user.user_management_service.service;

import com.management.user.user_management_service.model.Student;

import java.util.List;

public interface StudentServiceInterface
{
    Student createStudent(Student student);
    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student updateStudent(int id, Student student);
    void deleteStudent(int id);
}

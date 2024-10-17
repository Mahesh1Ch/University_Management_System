package com.management.user.user_management_service.repositoty;

import com.management.user.user_management_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>
{

}

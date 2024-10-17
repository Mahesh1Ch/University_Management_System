package com.management.examination.examination_service.repository;

import com.management.examination.examination_service.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository  extends JpaRepository<Exam,String>
{

}

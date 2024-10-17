package com.management.examination.examination_service.DataInitializer;

import com.management.examination.examination_service.model.Exam;
import com.management.examination.examination_service.repository.ExamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ExamDataInitializer implements CommandLineRunner {

    private final ExamRepository examRepository;

    public ExamDataInitializer(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Predefined exams for CSE from SEM 1 to SEM 4
        Exam exam1 = new Exam("SEM1CSE1", "SEM ONE EXAM CSE ONE", "Introduction to Programming - Basic concepts.", 100.0, "CSE1001");
        Exam exam2 = new Exam("SEM1CSE2", "SEM ONE EXAM CSE TWO", "Data Structures - Fundamental data structures.", 100.0, "CSE1001");
        Exam exam3 = new Exam("SEM1CSE3", "SEM ONE EXAM CSE THREE", "Computer Organization - Basics of computer architecture.", 100.0, "CSE1001");
        Exam exam4 = new Exam("SEM1CSE4", "SEM ONE EXAM CSE FOUR", "Software Engineering - Principles and practices.", 100.0, "CSE1001");
        Exam exam5 = new Exam("SEM1CSE5", "SEM ONE EXAM CSE FIVE", "Database Management Systems - Introduction to DBMS.", 100.0, "CSE1001");
        Exam exam6 = new Exam("SEM1CSE6", "SEM ONE EXAM CSE SIX", "Web Technologies - Basics of web development.", 100.0, "CSE1001");
        Exam exam7 = new Exam("SEM1CSE7", "SEM ONE EXAM CSE SEVEN", "Operating Systems - Concepts and design.", 100.0, "CSE1001");
        Exam exam8 = new Exam("SEM1CSE8", "SEM ONE EXAM CSE EIGHT", "Computer Networks - Introduction to networking.", 100.0, "CSE1001");

        // SEM 2
        Exam exam9 = new Exam("SEM2CSE1", "SEM TWO EXAM CSE ONE", "Object-Oriented Programming - OOP concepts.", 100.0, "CSE1001");
        Exam exam10 = new Exam("SEM2CSE2", "SEM TWO EXAM CSE TWO", "Theory of Computation - Basics of computation theory.", 100.0, "CSE1001");
        Exam exam11 = new Exam("SEM2CSE3", "SEM TWO EXAM CSE THREE", "Compiler Design - Basics of compiler construction.", 100.0, "CSE1001");
        Exam exam12 = new Exam("SEM2CSE4", "SEM TWO EXAM CSE FOUR", "Microprocessors - Architecture and programming.", 100.0, "CSE1001");
        Exam exam13 = new Exam("SEM2CSE5", "SEM TWO EXAM CSE FIVE", "Artificial Intelligence - Introduction to AI concepts.", 100.0, "CSE1001");
        Exam exam14 = new Exam("SEM2CSE6", "SEM TWO EXAM CSE SIX", "Data Mining - Techniques and applications.", 100.0, "CSE1001");
        Exam exam15 = new Exam("SEM2CSE7", "SEM TWO EXAM CSE SEVEN", "Mobile Application Development - Basics of mobile apps.", 100.0, "CSE1001");
        Exam exam16 = new Exam("SEM2CSE8", "SEM TWO EXAM CSE EIGHT", "Software Testing - Principles and techniques.", 100.0, "CSE1001");

        // SEM 3
        Exam exam17 = new Exam("SEM3CSE1", "SEM THREE EXAM CSE ONE", "Cloud Computing - Concepts and services.", 100.0, "CSE1001");
        Exam exam18 = new Exam("SEM3CSE2", "SEM THREE EXAM CSE TWO", "Cyber Security - Introduction to security practices.", 100.0, "CSE1001");
        Exam exam19 = new Exam("SEM3CSE3", "SEM THREE EXAM CSE THREE", "Machine Learning - Introduction and algorithms.", 100.0, "CSE1001");
        Exam exam20 = new Exam("SEM3CSE4", "SEM THREE EXAM CSE FOUR", "Big Data Technologies - Concepts and tools.", 100.0, "CSE1001");
        Exam exam21 = new Exam("SEM3CSE5", "SEM THREE EXAM CSE FIVE", "Advanced Web Development - Frameworks and tools.", 100.0, "CSE1001");
        Exam exam22 = new Exam("SEM3CSE6", "SEM THREE EXAM CSE SIX", "Data Science - Techniques and applications.", 100.0, "CSE1001");
        Exam exam23 = new Exam("SEM3CSE7", "SEM THREE EXAM CSE SEVEN", "Mobile Networks - Concepts and applications.", 100.0, "CSE1001");
        Exam exam24 = new Exam("SEM3CSE8", "SEM THREE EXAM CSE EIGHT", "Human-Computer Interaction - Principles and design.", 100.0, "CSE1001");

        // SEM 4
        Exam exam25 = new Exam("SEM4CSE1", "SEM FOUR EXAM CSE ONE", "Software Testing - Principles and techniques.", 100.0, "CSE1001");
        Exam exam26 = new Exam("SEM4CSE2", "SEM FOUR EXAM CSE TWO", "Cloud Computing - Concepts and services.", 100.0, "CSE1001");
        Exam exam27 = new Exam("SEM4CSE3", "SEM FOUR EXAM CSE THREE", "Cyber Security - Introduction to security practices.", 100.0, "CSE1001");
        Exam exam28 = new Exam("SEM4CSE4", "SEM FOUR EXAM CSE FOUR", "Machine Learning - Introduction and algorithms.", 100.0, "CSE1001");
        Exam exam29 = new Exam("SEM4CSE5", "SEM FOUR EXAM CSE FIVE", "Advanced Software Engineering - Techniques and practices.", 100.0, "CSE1001");
        Exam exam30 = new Exam("SEM4CSE6", "SEM FOUR EXAM CSE SIX", "Distributed Systems - Principles and design.", 100.0, "CSE1001");
        Exam exam31 = new Exam("SEM4CSE7", "SEM FOUR EXAM CSE SEVEN", "Data Analytics - Concepts and tools.", 100.0, "CSE1001");
        Exam exam32 = new Exam("SEM4CSE8", "SEM FOUR EXAM CSE EIGHT", "Network Security - Practices and protocols.", 100.0, "CSE1001");

        // Save predefined exams to the database
        examRepository.saveAll(Arrays.asList(
                exam1, exam2, exam3, exam4, exam5, exam6, exam7, exam8,
                exam9, exam10, exam11, exam12, exam13, exam14, exam15, exam16,
                exam17, exam18, exam19, exam20, exam21, exam22, exam23, exam24,
                exam25, exam26, exam27, exam28, exam29, exam30, exam31, exam32
        ));
    }


}


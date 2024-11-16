package com.management.course.course_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.management.course.course_management_service.entity", "com.management.user.user_management_service.model"})
@SpringBootApplication
public class CourseManagementServiceApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CourseManagementServiceApplication.class, args);
		System.out.println("Hello world");
	}

}

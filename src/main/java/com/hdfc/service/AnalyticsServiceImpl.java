package com.hdfc.service;

import com.hdfc.repository.CourseRepository;
import com.hdfc.repository.EnrollmentRepository;
import com.hdfc.entity.Enrollment;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final CourseRepository courseRepository = new CourseRepository();
    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    @Override
    public long getCourseCount() {
        return courseRepository.getStore().size();
    }

    @Override
    public long getEnrollmentCount() {
        return enrollmentRepository.getStore().size();
    }

    @Override
    public String getMostPopularCourse() {

        return enrollmentRepository.findAll().stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseId, Collectors.counting()))
                .entrySet()
                .stream()
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .map(e -> "Most popular course ID: " + e.getKey() + " (Enrollments: " + e.getValue() + ")")
                .orElse("No enrollments yet");
    }
}
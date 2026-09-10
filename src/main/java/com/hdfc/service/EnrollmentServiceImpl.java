package com.hdfc.service;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.entity.Course;
import com.hdfc.entity.Enrollment;
import com.hdfc.exception.CourseCapacityFullException;
import com.hdfc.exception.CourseNotFoundException;
import com.hdfc.exception.DuplicateEnrollmentException;
import com.hdfc.exception.EnrollmentNotFoundException;
import com.hdfc.mapper.EnrollmentMapper;
import com.hdfc.repository.CourseRepository;
import com.hdfc.repository.EnrollmentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final CourseRepository courseRepository = new CourseRepository();
    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    @Override
    public EnrollmentResponseDto enrollEmployee(EnrollmentRequestDto dto) {

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course does not exist"));

        long enrolledCount = enrollmentRepository.getStore().values().stream()
                .filter(e -> e.getCourseId().equals(dto.getCourseId()))
                .count();

        if (enrolledCount >= course.getMaxCapacity())
            throw new CourseCapacityFullException("Course capacity full!");

        boolean exists = enrollmentRepository.getStore().values().stream()
                .anyMatch(e -> e.getEmployeeId().equals(dto.getEmployeeId())
                        && e.getCourseId().equals(dto.getCourseId()));

        if (exists)
            throw new DuplicateEnrollmentException("Employee already enrolled");

        Enrollment enrollment = new Enrollment();
        enrollment.setEmployeeId(dto.getEmployeeId());
        enrollment.setEmployeeName(dto.getEmployeeName());
        enrollment.setCourseId(String.valueOf(dto.getCourseId()));
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus("ENROLLED");

        Enrollment saved = enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(saved);
    }

    @Override
    public List<EnrollmentResponseDto> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponseDto getEnrollmentById(Integer id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
        return EnrollmentMapper.toResponse(enrollment);
    }

    @Override
    public EnrollmentResponseDto cancelEnrollment(Integer id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
        enrollment.setStatus("CANCELLED");
        return EnrollmentMapper.toResponse(enrollment);
    }

    @Override
    public EnrollmentResponseDto completeEnrollment(Integer id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
        enrollment.setStatus("COMPLETED");
        return EnrollmentMapper.toResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollmentsByStatus(String status) {
        return enrollmentRepository.findAll().stream()
                .filter(e -> e.getStatus().equalsIgnoreCase(status))
                .map(EnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollmentsByEmployeeId(Integer employeeId) {
        return enrollmentRepository.findAll().stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .map(EnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String getMostPopularCourse() {
        return enrollmentRepository.findAll().stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseId, Collectors.counting()))
                .entrySet().stream()
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .map(e -> "Most popular course ID is: " + e.getKey())
                .orElse("No enrollments yet");
    }
}
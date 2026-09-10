package com.hdfc.controller;

import com.hdfc.dto.EnrollmentRequestDto;
import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.service.EnrollmentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public EnrollmentResponseDto enrollEmployee(@RequestBody EnrollmentRequestDto dto) {
        return enrollmentService.enrollEmployee(dto);
    }

    @GetMapping
    public List<EnrollmentResponseDto> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public EnrollmentResponseDto getEnrollmentById(@PathVariable Integer id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PutMapping("/{id}/cancel")
    public EnrollmentResponseDto cancelEnrollment(@PathVariable Integer id) {
        return enrollmentService.cancelEnrollment(id);
    }

    @PutMapping("/{id}/complete")
    public EnrollmentResponseDto completeEnrollment(@PathVariable Integer id) {
        return enrollmentService.completeEnrollment(id);
    }

    @GetMapping("/status/{status}")
    public List<EnrollmentResponseDto> getByStatus(@PathVariable String status) {
        return enrollmentService.getEnrollmentsByStatus(status);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EnrollmentResponseDto> getByEmployeeId(@PathVariable Integer employeeId) {
        return enrollmentService.getEnrollmentsByEmployeeId(employeeId);
    }

    @GetMapping("/popular")
    public String mostPopularCourse() {
        return enrollmentService.getMostPopularCourse();
    }
}
package com.hdfc.mapper;

import com.hdfc.dto.EnrollmentResponseDto;
import com.hdfc.entity.Enrollment;

public class EnrollmentMapper {

    public static EnrollmentResponseDto toResponse(Enrollment e) {
        EnrollmentResponseDto dto = new EnrollmentResponseDto();
        dto.setEnrollmentId(e.getEnrollmentId());
        dto.setEmployeeId(e.getEmployeeId());
        dto.setEmployeeName(e.getEmployeeName());
        dto.setCourseId(Integer.valueOf(e.getCourseId()));
        dto.setEnrollmentDate(e.getEnrollmentDate());
        dto.setStatus(e.getStatus());
        return dto;
    }
}
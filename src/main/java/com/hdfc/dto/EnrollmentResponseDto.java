package com.hdfc.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EnrollmentResponseDto {

    private Integer enrollmentId;
    private Integer employeeId;
    private String employeeName;
    private Integer courseId;
    private LocalDate enrollmentDate;
    private String status;
}

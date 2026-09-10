package com.hdfc.entity;

import lombok.Data;
import java.time.LocalDate;


@Data
public class Enrollment {
    private Integer enrollmentId;

    private Integer employeeId;

    private String employeeName;

    private String courseId;

    private LocalDate enrollmentDate;

    private String status;
}

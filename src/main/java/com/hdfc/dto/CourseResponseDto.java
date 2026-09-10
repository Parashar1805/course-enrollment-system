package com.hdfc.dto;

import lombok.Data;

@Data
public class CourseResponseDto {
    private Integer courseId;

    private String courseName;

    private String trainerName;

    private Integer durationInDays;

    private Integer maxCapacity;

    private Double fees;
}

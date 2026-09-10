package com.hdfc.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Course {
    private Integer courseId;

    @NotBlank
    private String courseName;

    @NotBlank
    private String trainerName;

    @Positive
    private Integer durationInDays;

    @Positive
    private Integer maxCapacity;

    @Positive
    private Double fees;
}

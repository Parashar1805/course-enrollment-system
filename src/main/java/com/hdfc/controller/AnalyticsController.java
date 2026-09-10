package com.hdfc.controller;

import com.hdfc.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/course-count")
    public long getCourseCount() {
        return analyticsService.getCourseCount();
    }

    @GetMapping("/enrollment-count")
    public long getEnrollmentCount() {
        return analyticsService.getEnrollmentCount();
    }

    @GetMapping("/most-popular-course")
    public String getMostPopularCourse() {
        return analyticsService.getMostPopularCourse();
    }
}
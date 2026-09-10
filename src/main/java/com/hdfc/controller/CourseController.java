package com.hdfc.controller;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.service.CourseService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponseDto createCourse(@Valid @RequestBody CourseRequestDto dto) {
        return courseService.createCourse(dto);
    }

    @GetMapping("/{id}")
    public CourseResponseDto getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<CourseResponseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PutMapping("/{id}")
    public CourseResponseDto updateCourse(@PathVariable Integer id, @Valid @RequestBody CourseRequestDto dto) {
        return courseService.updateCourse(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("/trainer/{trainerName}")
    public List<CourseResponseDto> getByTrainer(@PathVariable String trainerName) {
        return courseService.getCoursesByTrainer(trainerName);
    }

    @GetMapping("/fees/{amount}")
    public List<CourseResponseDto> getByFees(@PathVariable Double amount) {
        return courseService.getCoursesByFeesLessThan(amount);
    }
}
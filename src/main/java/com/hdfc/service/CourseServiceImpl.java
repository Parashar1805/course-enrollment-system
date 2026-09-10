package com.hdfc.service;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;
import com.hdfc.exception.CourseNotFoundException;
import com.hdfc.mapper.CourseMapper;
import com.hdfc.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository = new CourseRepository();

    @Override
    public CourseResponseDto createCourse(CourseRequestDto dto) {
        Course course = CourseMapper.toEntity(dto);
        Course saved = courseRepository.save(course);
        return CourseMapper.toResponse(saved);
    }

    @Override
    public CourseResponseDto getCourseById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + id));
        return CourseMapper.toResponse(course);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto updateCourse(Integer id, CourseRequestDto dto) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + id));

        existing.setCourseName(dto.getCourseName());
        existing.setTrainerName(dto.getTrainerName());
        existing.setDurationInDays(dto.getDurationInDays());
        existing.setMaxCapacity(dto.getMaxCapacity());
        existing.setFees(dto.getFees());

        return CourseMapper.toResponse(existing);
    }

    @Override
    public String deleteCourse(Integer id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + id));

        courseRepository.delete(id);
        return "Course deleted successfully.";
    }

    @Override
    public List<CourseResponseDto> getCoursesByTrainer(String trainerName) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getTrainerName().equalsIgnoreCase(trainerName))
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDto> getCoursesByFeesLessThan(Double amount) {
        return courseRepository.findAll().stream()
                .filter(c -> c.getFees() < amount)
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
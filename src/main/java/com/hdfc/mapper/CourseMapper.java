package com.hdfc.mapper;

import com.hdfc.dto.CourseRequestDto;
import com.hdfc.dto.CourseResponseDto;
import com.hdfc.entity.Course;

public class CourseMapper {
    public static Course toEntity(CourseRequestDto dto){
        Course c=new Course();
        c.setCourseName(dto.getCourseName());
        c.setTrainerName(dto.getTrainerName());
        c.setDurationInDays(dto.getDurationInDays());
        c.setMaxCapacity(dto.getMaxCapacity());
        c.setFees(dto.getFees());
        return c;
    }

    public static CourseResponseDto toResponse(Course c){
        CourseResponseDto dto = new CourseResponseDto();
        dto.setCourseId(c.getCourseId());
        dto.setCourseName(c.getCourseName());
        dto.setTrainerName(c.getTrainerName());
        dto.setDurationInDays(c.getDurationInDays());
        dto.setMaxCapacity(c.getMaxCapacity());
        dto.setFees(c.getFees());
        return dto;
    }
}

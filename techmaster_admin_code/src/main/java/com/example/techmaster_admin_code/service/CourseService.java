package com.example.techmaster_admin_code.service;

import com.example.techmaster_admin_code.model.Course;
import com.example.techmaster_admin_code.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<Course> getAllCourse(Integer page, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(page - 1, pageSize);
        return courseRepository.findAll(pageRequest);
    }

    public Page<Course> getAllCourse(Integer page, Integer pageSize, String type) {
        Pageable pageRequest = PageRequest.of(page - 1, pageSize);
        return courseRepository.findByType(pageRequest, type);
    }

    public List<Course> getAllCourse(String type) {
        return courseRepository.findByType(type);
    }

    public Course getCourseDetail(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found course with id = " + id);
                });
    }
}

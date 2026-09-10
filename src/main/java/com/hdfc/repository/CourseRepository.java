package com.hdfc.repository;

import com.hdfc.entity.Course;
import java.util.*;

public class CourseRepository {
    private Map<Integer, Course> store=new HashMap<>();
    private int counter=1;

    public Course save(Course c){
        c.setCourseId(counter++);
        store.put(c.getCourseId(),c);
        return c;
    }

    public Optional<Course> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Course> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(Integer id) {
        store.remove(id);
    }

    public Map<Integer, Course> getStore() {
        return store;
    }
}

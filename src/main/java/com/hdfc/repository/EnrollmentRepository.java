package com.hdfc.repository;

import com.hdfc.entity.Enrollment;
import java.util.*;

public class EnrollmentRepository {

    private Map<Integer, Enrollment> store = new HashMap<>();
    private int counter = 1;

    public Enrollment save(Enrollment e) {
        e.setEnrollmentId(counter++);
        store.put(e.getEnrollmentId(), e);
        return e;
    }

    public Optional<Enrollment> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(store.values());
    }

    public Map<Integer, Enrollment> getStore() {
        return store;
    }
}
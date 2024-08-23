package com.example.demo.service;

import com.example.demo.entity.Department_facility;
import com.example.demo.repository.Department_facilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Department_facilityService {
    @Autowired
    Department_facilityRepo repo;

    public Page<Department_facility> getPage(Integer page) {
        int size = 5;
        if (page < 0) {
            page = 0;
        }
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p);
    }
}

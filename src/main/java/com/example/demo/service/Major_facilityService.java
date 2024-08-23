package com.example.demo.service;


import com.example.demo.repository.Major_facilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Major_facilityService {
    @Autowired
    Major_facilityRepo repo;
}

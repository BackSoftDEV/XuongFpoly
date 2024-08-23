package com.example.demo.repository;

import com.example.demo.entity.Department_facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Department_facilityRepo extends JpaRepository<Department_facility,String> {
}

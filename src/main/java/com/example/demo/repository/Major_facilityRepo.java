package com.example.demo.repository;

import com.example.demo.entity.Major_facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Major_facilityRepo extends JpaRepository<Major_facility,String> {
}

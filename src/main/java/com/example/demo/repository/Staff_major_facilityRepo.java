package com.example.demo.repository;

import com.example.demo.entity.Staff_major_facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Staff_major_facilityRepo extends JpaRepository<Staff_major_facility, String> {

}

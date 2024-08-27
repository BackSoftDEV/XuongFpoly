package com.example.demo.repository;


import com.example.demo.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<Staff,String> {
    List<Staff> findByNameContaining(String name);
    Page<Staff> findByStatus(Integer status, Pageable pageable);
}

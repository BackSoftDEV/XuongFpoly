package com.example.demo.service;


import com.example.demo.entity.Staff;
import com.example.demo.entity.Staff_major_facility;
import com.example.demo.repository.StaffRepo;
import com.example.demo.repository.Staff_major_facilityRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Staff_major_facilityService {
    @Autowired
    Staff_major_facilityRepo reposmf;
    @Autowired
    private StaffRepo repo;
        public Page<Staff> getPage(Integer page,Integer size){
//        int size = 5;
//        if (page < 0) {
//            page = 0;
//        }
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p);
    }

//    public Page<Staff_major_facility> getPage(Integer page, Integer size) {
//        if (page < 0) {
//            page = 0;
//        }
//        Pageable p = PageRequest.of(page, size);
//        return repo.findAll(p);
//    }

//    public Staff add(Staff staff){
//        return repo.save(staff);
//    }
public Staff add(@Valid Staff staff) {
    return repo.save(staff);
}
    public Staff delete(String id){
        Optional<Staff> optional = repo.findById(id);
        return optional.map(o->{
            repo.delete(o);
            return o;
        }).orElse(null);
    }
    public Staff update(String id,Staff a){
        Optional<Staff> optional = repo.findById(id);
        return optional.map(o->{
            o.setAccountFe(a.getAccountFe());
            o.setCreatedDate(a.getCreatedDate());
            o.setAccountFpt(a.getAccountFpt());
            o.setStaffCode(a.getStaffCode());
            o.setStatus(a.getStatus());
            o.setName(a.getName());
            o.setLastModifiedDate(a.getLastModifiedDate());
            return repo.save(o);
        }).orElse(null);
    }

    public Staff detail(String id){
        return repo.findById(id).orElse(null);
    }

    public List<Staff> search(String name){
            return repo.findByNameContaining(name);
    }
}


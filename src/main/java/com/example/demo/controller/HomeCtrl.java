package com.example.demo.controller;


import com.example.demo.entity.Staff;
import com.example.demo.service.Department_facilityService;
import com.example.demo.service.Staff_major_facilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/api/xuongjava")
public class HomeCtrl {
    @Autowired
    Department_facilityService servicedf;
    @Autowired
    Staff_major_facilityService servicesmf;

    @GetMapping("/df")
    public ResponseEntity<?> getPagedf(@RequestParam(defaultValue = "0", name = "pagedf") Integer a) {
        return ResponseEntity.ok(servicedf.getPage(a));
    }

    //        @GetMapping("/smf")
//    public ResponseEntity<?> getPagesmf(@RequestParam(defaultValue = "o",name = "pagesmf")Integer b,
//                                        @RequestParam(defaultValue = "5",name = "size")Integer c){
//        return ResponseEntity.ok(servicesmf.getPage(b,c));
//    }
//    @GetMapping("/staff")
//    public String viewStaffs(@RequestParam(defaultValue = "0", name = "page") Integer page,
//                             @RequestParam(defaultValue = "10", name = "size") Integer size,
//                             Model model) {
//
//        var staffPage = servicesmf.getPage(page, size);
//
//
//        model.addAttribute("staffs", staffPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", staffPage.getTotalPages());
//
//
//        return "view/viewqlnv";
//    }
    @GetMapping("/staff")
    public ResponseEntity<?> getPageStaff(@RequestParam(defaultValue = "0", name = "pageStaff") Integer b,
                                          @RequestParam(defaultValue = "5", name = "size") Integer a) {
        return ResponseEntity.ok(servicesmf.getPage(b, a));
    }

    @GetMapping("/staff-management")
    public String showStaffManagementPage() {
        return "redirect:/view/staff-management.html";
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Staff a) {
        return ResponseEntity.ok(servicesmf.add(a));
    }
//    @PostMapping("/addStaff")
//    public String addStaff(@Valid @ModelAttribute("staff") Staff staff, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-staff-form";
//        }
//        return "redirect:/view/staff-management.html";
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(servicesmf.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Staff a) {
        return ResponseEntity.ok(servicesmf.update(id, a));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable String id) {
        return ResponseEntity.ok(servicesmf.detail(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Staff>> search(@RequestParam(value = "name",defaultValue = "")String name){
        List<Staff> a = servicesmf.search(name);
        return ResponseEntity.ok(a);
    }
//    @GetMapping("/download-template")
//    public ResponseEntity<Resource> downloadTemplate() {
//        try {
//            Resource resource = new ClassPathResource("templates/template.xlsx");
//            if (resource.exists()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                        .body(resource);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
@GetMapping("/download-template")
public ResponseEntity<Resource> downloadTemplate() {
    try {
        Resource resource = new ClassPathResource("templates/template.xlsx");
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().build();
    }
}



}

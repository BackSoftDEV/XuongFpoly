package com.example.demo.controller;


import com.example.demo.entity.Staff;
import com.example.demo.service.Department_facilityService;
import com.example.demo.service.Staff_major_facilityService;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
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


//    @GetMapping("/staff")
//    public ResponseEntity<?> getPageStaff(@RequestParam(defaultValue = "0", name = "pageStaff") Integer b,
//                                          @RequestParam(defaultValue = "5", name = "size") Integer a) {
//        return ResponseEntity.ok(servicesmf.getPage(b, a));
//    }
@GetMapping("/staff")
public ResponseEntity<?> getPageStaff(
        @RequestParam(defaultValue = "0", name = "pageStaff") Integer b,
        @RequestParam(defaultValue = "5", name = "size") Integer a,
        @RequestParam(required = false, name = "status") Integer status) {

    if (status != null) {
        return ResponseEntity.ok(servicesmf.getStaffByStatus(PageRequest.of(b, a), status));
    } else {
        return ResponseEntity.ok(servicesmf.getPage(b, a));
    }
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

    //
//    @GetMapping("/search/{name}")
//    public ResponseEntity<List<Staff>> search(@RequestParam(value = "name",defaultValue = "")String name){
//        List<Staff> a = servicesmf.search(name);
//        return ResponseEntity.ok(a);
//    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Staff>> search(@PathVariable String name) {
        List<Staff> a = servicesmf.search(name);
        return ResponseEntity.ok(a);
    }
//
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
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//    }
@GetMapping("/download-template")
public ResponseEntity<ByteArrayResource> downloadTemplate() {
    try {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Staff Data");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Mã nhân viên");
        header.createCell(1).setCellValue("Tên nhân viên");
        header.createCell(2).setCellValue("Email FPT");
        header.createCell(3).setCellValue("Email FE");
        header.createCell(4).setCellValue("Trạng thái");


        List<Staff> staffList = servicesmf.getAllStaff();


        int rowIndex = 1;
        for (Staff staff : staffList) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(staff.getStaffCode());
            row.createCell(1).setCellValue(staff.getName());
            row.createCell(2).setCellValue(staff.getAccountFpt());
            row.createCell(3).setCellValue(staff.getAccountFe());
            row.createCell(4).setCellValue(staff.getStatus() == 1 ? "Đang hoạt động" : "Ngừng hoạt động");
        }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=staff_data.xlsx")
                .body(byteArrayResource);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().build();
    }
}
    @PostMapping("/import-staff")
    public ResponseEntity<?> importStaff(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Staff staff = new Staff();
                staff.setStaffCode(row.getCell(0).getStringCellValue());
                staff.setName(row.getCell(1).getStringCellValue());
                staff.setAccountFpt(row.getCell(2).getStringCellValue());
                staff.setAccountFe(row.getCell(3).getStringCellValue());
                staff.setStatus(row.getCell(4).getStringCellValue().equals("Đang hoạt động") ? 1 : 0);

                servicesmf.add(staff);
            }

            workbook.close();
            return ResponseEntity.ok("Import thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Import thất bại.");
        }
    }


}

package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "staff")
public class Staff {
    @Id
    private String id;

    @NotNull(message = "Trạng thái không được để trống")
    private Integer status;

    @Column(name = "created_date")
    private Integer createdDate;

    @Column(name = "last_modified_date")
    private Integer lastModifiedDate;

    @NotBlank(message = "Email FE không được để trống")
    @Email(message = "Email FE phải hợp lệ và có đuôi @fe.edu.vn", regexp = "^[^\\s@]+@fe\\.edu\\.vn$")
    @Length(max = 100, message = "Email FE phải có độ dài nhỏ hơn 100 ký tự")
    @Column(name = "account_fe")
    private String accountFe;

    @NotBlank(message = "Email FPT không được để trống")
    @Email(message = "Email FPT phải hợp lệ và có đuôi @fpt.edu.vn", regexp = "^[^\\s@]+@fpt\\.edu\\.vn$")
    @Length(max = 100, message = "Email FPT phải có độ dài nhỏ hơn 100 ký tự")
    @Column(name = "account_fpt")
    private String accountFpt;

    @NotBlank(message = "Tên không được để trống")
    @Length(max = 100, message = "Tên phải có độ dài nhỏ hơn 100 ký tự")
    private String name;

    @NotBlank(message = "Mã nhân viên không được để trống")
    @Length(max = 15, message = "Mã nhân viên phải có độ dài nhỏ hơn 15 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Mã nhân viên không được chứa khoảng trắng hoặc ký tự tiếng Việt")
    @Column(name = "staff_code")
    private String staffCode;

    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}

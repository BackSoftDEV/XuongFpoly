package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "facility")
public class Facility {
    @Id
    private String id;

    private Integer status;

    @Column(name = "created_date")
    private Integer createdDate;

    @Column(name = "last_modified_date")
    private Integer lastModifiedDate;

    private String code;

    private String name;
}

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "department_facility")
public class Department_facility {
    @Id
    private String id;

    private Integer status;

    @Column(name = "created_date")
    private Integer createdDate;

    @Column(name = "last_modified_date")
    private Integer lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "id_facility")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;
}

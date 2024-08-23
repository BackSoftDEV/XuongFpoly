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
@Table(name = "major_facility")
public class Major_facility {
    @Id
    private String id;

    private Integer status;

    @Column(name = "created_date")
    private Integer createdDate;

    @Column(name = "last_modified_date")
    private Integer lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "id_department_facility")
    private Department_facility departmentFacility;

    @ManyToOne
    @JoinColumn(name = "id_major")
    private Major major;
}

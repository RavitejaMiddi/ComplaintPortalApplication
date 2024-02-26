package com.portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.enums.EngineerDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "engineer_tbl") //@Table annotation specifies the name of the database table to be used for mapping
public class Engineer {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id//@Id annotation specifies the primary key of an entity
    @Column(name = "ENGINEER_ID")
    private int engineerId; // treat like login id

    private String engineerName;


    private EngineerDomain domain; // like washing machine , AC, Mobile phone


    @JsonIgnore //@JsonIgnore annotation is used at the class level to ignore fields
    @OneToMany(mappedBy = "engineer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints = new ArrayList<>();



}
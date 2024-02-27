package com.portal.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portal.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaint_tbl")  //@Table annotation specifies the name of the database table to be used for mapping
public class Complaint {


    @Id //@Id annotation specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_Sequence")
    @SequenceGenerator(name = "complaint_Sequence", sequenceName = "complaint_seq", allocationSize = 1, initialValue = 1)
    private int complaintId;


    //@NotNull annotation specifies that the value should hold null value
    @Column(name = "productModelNumber")
    private String productModelNumber;

    @Column(name = "complaintName")
    @Length(min = 5, max = 15, message = "Length of Complaint Name should be between 5 and 15")
    private String complaintName;


    @Column(name = "status")        //@Column annotation is used to define the name of column
    private ComplaintStatus status; // open , resolve online , resolve after home visit , resolved , closed

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engineer_engineer_id")
    private Engineer engineer;














}

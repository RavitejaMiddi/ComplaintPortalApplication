package com.portal.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.portal.enums.ProductCategoryName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //It checks class it creates a table
@Table(name = "product_tbl") //It changes a table name in database with name as prd_tbl.
public class Product {
    //It is primary id to get data from table  and not null and should match pattern
    @Id
    @Pattern(regexp = "[A-Z]{4}[0-9]{4}", message = "Model number should be like  IRED3456")
    private String modelNumber;

    private String productName;

    private ProductCategoryName productCategoryName; // washing machine , TV,AC,SmartPhone

    //It should be in format like 1999-10-09
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfPurchase;

    //warranty years not null
    private int warrentyYears;

    private LocalDate warrantyDate; // should be auto generated


    // Method is used to get the warranty date.
    public LocalDate getWarrantyDate() {
        return getDateOfPurchase().plusYears(getWarrentyYears());

       }


}
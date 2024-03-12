package com.example.exercisevalidation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Project {
    @NotEmpty(message = "ID should not Empty")
    @Min(value = 2,message = "Length more than 8")
    private String ID;
    @NotEmpty(message = "Title Cannot be null")
    @Size(min=8, message = "Length more than 8")
    private  String title;
    @NotEmpty(message = "Description Cannot be null")
    @Size(min=15, message = "Length more than 15")
    private String description ;
    @NotEmpty(message = "Status Cannot be null")
  //||
    @Pattern(regexp="^(Not Started|Progress|Completed)$",message = "Choose ONLY THIS 1-Not Started 2-Progress 3-Completed only")
    private String status;
    @NotEmpty(message = "Company Name Cannot be null")
    @Size(min = 6,message = "company length 6")
    private  String companyName;

}

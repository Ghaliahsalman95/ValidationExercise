package com.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data@AllArgsConstructor
public class Event {
    @NotEmpty(message = "ID not should be empty")
    @Size(min =2,message ="ID length more than 15")
    private String ID ;
    @NotEmpty(message = "description not should be empty")
    @Size(min = 15,message ="description length more than 15" )
    private String description ;
    @NotNull(message = "capacity not should be empty")
    @Positive(message = "Only number 0 or greater ")
    @Min(25)
    private Integer capacity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate , endDate;
}

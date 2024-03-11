package com.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data@AllArgsConstructor
public class Event {
    @NotEmpty(message = "ID not should be empty")@Size(min =2)
    private String ID ;
    @NotEmpty(message = "description not should be empty")@Size(min = 15)
            private String description ;
    @NotEmpty(message = "capacity not should be empty")@Size(min =25)@Positive(message = "Only number 0 or greater ")
    private int capacity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate , endDate;
}

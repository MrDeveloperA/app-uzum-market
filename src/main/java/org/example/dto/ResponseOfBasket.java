package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class ResponseOfBasket {
    private String name;
    private double price;
    private Timestamp createdDate;
    private String color;
    private String brand;
    private String description;
    private String size;
    private int quantity;
}

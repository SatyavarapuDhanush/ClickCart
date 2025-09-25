package com.clickcart.app.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private double price;
    private Integer stock;
    private String category;
    private String imageUrl;
}

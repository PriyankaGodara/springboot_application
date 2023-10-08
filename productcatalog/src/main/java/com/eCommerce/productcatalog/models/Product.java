package com.eCommerce.productcatalog.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}

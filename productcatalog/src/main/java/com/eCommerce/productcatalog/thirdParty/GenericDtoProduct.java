package com.eCommerce.productcatalog.thirdParty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericDtoProduct {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public void setId(Long id) {
    }
}

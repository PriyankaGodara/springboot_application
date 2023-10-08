package com.eCommerce.productcatalog.thirdPartyClients.productServices.fakeStore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}

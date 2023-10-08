package com.eCommerce.productcatalog.services;

import com.eCommerce.productcatalog.exceptions.NotFoundException;
import com.eCommerce.productcatalog.models.Product;
import com.eCommerce.productcatalog.thirdParty.GenericDtoProduct;

import java.util.List;

public interface ProductService {

   GenericDtoProduct createProduct(GenericDtoProduct product);

   GenericDtoProduct getProductById(Long id) throws  NotFoundException;
   List<GenericDtoProduct> getAllProducts();

   GenericDtoProduct updateProductById(Long id, GenericDtoProduct product);

   GenericDtoProduct deleteById(Long id);
   List<String> getAllCategories();

   GenericDtoProduct getByCategory(String category);

}

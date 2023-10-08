package com.eCommerce.productcatalog.services;

import com.eCommerce.productcatalog.thirdParty.GenericDtoProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericDtoProduct getProductById(Long id) {
        return null;
    }
    @Override
    public GenericDtoProduct createProduct(GenericDtoProduct product) {
        return null;
    }
    @Override
    public List<GenericDtoProduct> getAllProducts() {
        return null;
    }

    @Override
    public GenericDtoProduct updateProductById(Long id, GenericDtoProduct product) {
        return null;
    }

    @Override
    public GenericDtoProduct deleteById(Long id) {
        return null;
    }

    @Override
    public GenericDtoProduct getByCategory(String category) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }
}

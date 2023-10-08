package com.eCommerce.productcatalog.services;

import com.eCommerce.productcatalog.exceptions.NotFoundException;
import com.eCommerce.productcatalog.thirdParty.GenericDtoProduct;
import com.eCommerce.productcatalog.thirdPartyClients.productServices.fakeStore.FakeProductDto;
import com.eCommerce.productcatalog.thirdPartyClients.productServices.fakeStore.FakeShopClientsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeShopClientsServices fakeShopClientsServices;

    @Autowired
    public FakeStoreProductService(FakeShopClientsServices fakeShopClientsServices){
        this.fakeShopClientsServices = fakeShopClientsServices;
    }

    private GenericDtoProduct convertFakeProductInGenericDtoProduct(FakeProductDto fakeProductDto){

        GenericDtoProduct product = new GenericDtoProduct();
        product.setId(fakeProductDto.getId());
        product.setImage(fakeProductDto.getImage());
        product.setTitle(fakeProductDto.getTitle());
        product.setDescription(fakeProductDto.getDescription());
        product.setPrice(fakeProductDto.getPrice());
        product.setCategory(fakeProductDto.getCategory());

        return product;
    }

    @Override
    public  GenericDtoProduct createProduct(GenericDtoProduct product){
         return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.createProduct(product));
    }

    @Override
    public GenericDtoProduct getProductById(Long id) throws NotFoundException {
        return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.getProductById(id));
    }

    @Override
    public List<GenericDtoProduct> getAllProducts() {
        List<GenericDtoProduct> genericDtoProducts=new ArrayList<>();

        for(FakeProductDto fakeProductDto: fakeShopClientsServices.getAllProducts()){

            genericDtoProducts.add(convertFakeProductInGenericDtoProduct(fakeProductDto));
        }
       return genericDtoProducts;
    }
    @Override
    public GenericDtoProduct updateProductById(Long id, GenericDtoProduct product) {

        return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.updateProductById(id,product));
    }

    @Override
    public GenericDtoProduct deleteById(Long id) {
        return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.deleteById(id));
    }
    @Override
    public List<String> getAllCategories(){
        return fakeShopClientsServices.getAllCategories();
    }

    @Override
    public GenericDtoProduct getByCategory(String category) {
        return null;
    }
}

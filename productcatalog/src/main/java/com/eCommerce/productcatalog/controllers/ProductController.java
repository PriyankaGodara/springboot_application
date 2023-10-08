package com.eCommerce.productcatalog.controllers;

import com.eCommerce.productcatalog.exceptions.NotFoundException;
import com.eCommerce.productcatalog.services.ProductService;
import com.eCommerce.productcatalog.thirdParty.ExceptionDTO;
import com.eCommerce.productcatalog.thirdParty.GenericDtoProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/products")

public class ProductController {
 // @Autowired
    //field injection
    private ProductService productService;

    //constructor injection------->this is best way for injection
    //public ProductController(@Qualifier("fakeStoreProductService") ProductService productService)
    //1 way use Qualifier annonation other wise use @Primary annotation on file fakeStoreProductService
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /*
    setter injection
     @Autowired
          public void setProductService(ProductService productService){
            this.productService = productService;
        }
    */

   // get/products {}
    @GetMapping
    public List<GenericDtoProduct> getAllProducts(){
        return productService.getAllProducts();
    }
    //localhost:8080/products/123
    @GetMapping("{id}")
    public GenericDtoProduct getProductById(@PathVariable("id") Long id) throws NotFoundException {

        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericDtoProduct> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
          productService.deleteById(id),
                HttpStatus.OK
    );
    }


    @PutMapping("{id}")
    public GenericDtoProduct updateProductById(@PathVariable("id") Long id,@RequestBody GenericDtoProduct product){
              return productService.updateProductById(id,product);
    }
    @PostMapping
    public GenericDtoProduct createProduct(@RequestBody GenericDtoProduct product){
         return productService.createProduct(product);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/category/{category}")
    public List<GenericDtoProduct> getByCategory(@PathVariable("category") String category){
        return (List<GenericDtoProduct>) productService.getByCategory(category);
    }
}

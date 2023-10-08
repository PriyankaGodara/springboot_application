package com.eCommerce.productcatalog.thirdPartyClients.productServices.fakeStore;

import com.eCommerce.productcatalog.exceptions.NotFoundException;
import com.eCommerce.productcatalog.thirdParty.GenericDtoProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeShopClientsServices {
    @Value("${fakeStore.api.url}")
    private String fakeStoreApiURL;
    @Value("${fakeStore.api.paths.product}")
    private String fakeStoreProductPathApi;

    private String getProductRequestUrl;
    private String productRequestsBaseUrl;
    private String allCategoriesUrl;
    private String specificCategoryUrl;

      private RestTemplateBuilder restTemplateBuilder;

    public FakeShopClientsServices(RestTemplateBuilder restTemplateBuilder,
                                   @Value("${fakeStore.api.url}") String getProductRequestUrl,
                                   @Value("${fakeStore.api.paths.product}") String productRequestsBaseUrl,
                                   @Value("${fakeStore.api.paths.allCategories}") String allCategoriesUrl,
                                   @Value("${fakeStore.api.paths.specific.category}") String specificCategoryUrl ){
        this.restTemplateBuilder = restTemplateBuilder;
        this.getProductRequestUrl = getProductRequestUrl + productRequestsBaseUrl;
        this.productRequestsBaseUrl = getProductRequestUrl + productRequestsBaseUrl + "/{id}";
        this.allCategoriesUrl = getProductRequestUrl + allCategoriesUrl;
        this.specificCategoryUrl = getProductRequestUrl + specificCategoryUrl + "/{category}";
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
    public FakeProductDto createProduct(GenericDtoProduct product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto> response =
                restTemplate.postForEntity(
                        productRequestsBaseUrl,product,FakeProductDto.class);

        return response.getBody();
    }


    public FakeProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //send a request and get a response from third party
        ResponseEntity<FakeProductDto> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeProductDto.class, id);

        FakeProductDto fakeProductDto = response.getBody();

        if(fakeProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return fakeProductDto;
    }


    public List<FakeProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeProductDto[]> response =
                restTemplate.getForEntity(productRequestsBaseUrl,FakeProductDto[].class);

        List<GenericDtoProduct> answer = new ArrayList<>();

        return Arrays.stream(response.getBody()).toList();
    }

    public FakeProductDto deleteById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDto.class);
        ResponseExtractor<ResponseEntity<FakeProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeProductDto.class);
        ResponseEntity<FakeProductDto> response = restTemplate.execute(
                getProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }

    public FakeProductDto updateProductById(Long id, GenericDtoProduct product) {
        RestTemplate restTemplate=restTemplateBuilder.build();


        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDto.class);
        ResponseExtractor<ResponseEntity<FakeProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeProductDto.class);

        ResponseEntity<FakeProductDto> response= restTemplate.execute(
                getProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id,product
        );

//        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        return response.getBody();
    }

    public List<String> getAllCategories() {
     RestTemplate restTemplate = restTemplateBuilder.build();
     ResponseEntity<List> response= restTemplate.getForEntity(allCategoriesUrl, List.class);
     return response.getBody().stream().toList();
    }

    public List<FakeProductDto> getByCategory(String category){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDto[]> response = restTemplate.getForEntity(getProductRequestUrl,FakeProductDto[].class,category);

        return Arrays.stream(response.getBody()).toList();
    }
}

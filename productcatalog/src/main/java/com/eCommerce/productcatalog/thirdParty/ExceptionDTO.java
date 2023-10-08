package com.eCommerce.productcatalog.thirdParty;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDTO {
    private HttpStatus errorCode;
    private String message;

    public ExceptionDTO(HttpStatus status, String message){
        this.errorCode = status;
        this.message = message;
    }
}
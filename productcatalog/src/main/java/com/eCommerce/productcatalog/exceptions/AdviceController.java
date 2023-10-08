package com.eCommerce.productcatalog.exceptions;

import com.eCommerce.productcatalog.thirdParty.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNotFoundException(
            NotFoundException notFoundException){
        //   System.out.println("Not found Exception happened.");
        return new ResponseEntity(
                new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}

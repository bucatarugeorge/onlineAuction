package com.sda.onlineAuction.validator;

import com.sda.onlineAuction.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ProductDtoValidator {

    //TODO create validators for the others fields.

    public void validate(ProductDto productDto, BindingResult bindingResult){
        validateDate(productDto, bindingResult);
        validatePrice(productDto, bindingResult);
        validateName(productDto,bindingResult);
    }

    private void validatePrice(ProductDto productDto, BindingResult bindingResult) {
        String priceAsString = productDto.getStartBiddingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger <= 0) {
                FieldError fieldError = new FieldError("productDto", "startBiddingPrice", "The price must be a positive number");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException numberFormatException) {
            FieldError fieldError = new FieldError("productDto", "startBiddingPrice", "The price must be a  number");
            bindingResult.addError(fieldError);

        }
    }

    public void validateDate(ProductDto productDto, BindingResult bindingResult) {
        LocalDateTime endDataTimeAsString = LocalDateTime.parse(productDto.getEndDateTime());

            if (endDataTimeAsString.isBefore(LocalDateTime.now().plusDays(1))) {
                FieldError fieldError = new FieldError("productDto", "endDateTime", "The end date must be more than 24 h from posting time");
                bindingResult.addError(fieldError);
            }
    }

    private void validateName(ProductDto productDto,BindingResult bindingResult){
        String name = productDto.getName();
        if(name.length()<5){
            FieldError fieldError = new FieldError("productDto","name","The product's name must have more than 4 letter!");
            bindingResult.addError(fieldError);
        }

    }

//    <label th:if="${#fields.hasErrors('startBiddingPrice')}" th:class="'error'" th:errors="*{startBiddingPrice}"
//                   class="col-lg-4 control-label" style="color:red;"></label>  //TODO


    public boolean isValid2(ProductDto productDto, BindingResult bindingResult) {
        String priceAsString = productDto.getStartBiddingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger <= 0) {
                return false;
            }
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }
}

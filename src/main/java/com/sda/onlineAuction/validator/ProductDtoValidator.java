package com.sda.onlineAuction.validator;

import com.sda.onlineAuction.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ProductDtoValidator {

    //TODO create validators for the others fields.

    public void validate(ProductDto productDto, BindingResult bindingResult) {
        String priceAsString = productDto.getStartBiddingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger <= 0) {
                FieldError fieldError = new FieldError("productDto","startBiddingPrice","The price must be a positive number");
                bindingResult.addError(fieldError);
            }
        } catch (NumberFormatException numberFormatException) {
            FieldError fieldError = new FieldError("productDto","startBiddingPrice","The price must be a  number");
            bindingResult.addError(fieldError);

        }
    }

//    <label th:if="${#fields.hasErrors('startBiddingPrice')}" th:class="'error'" th:errors="*{startBiddingPrice}"
//                   class="col-lg-4 control-label" style="color:red;"></label>  //TODO




    public boolean isValid2(ProductDto productDto,BindingResult bindingResult) {
        String priceAsString = productDto.getStartBiddingPrice();
        try {
            Integer priceAsInteger = Integer.valueOf(priceAsString);
            if (priceAsInteger <= 0) {
                return false;
            }
            } catch(NumberFormatException numberFormatException){
                return false;
            }
            return true;
        }
    }

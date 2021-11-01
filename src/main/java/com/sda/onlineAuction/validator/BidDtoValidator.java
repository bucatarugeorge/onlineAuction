package com.sda.onlineAuction.validator;

import com.sda.onlineAuction.dto.BidDto;
import com.sda.onlineAuction.model.Bid;
import com.sda.onlineAuction.model.Product;
import com.sda.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
public class BidDtoValidator {
    @Autowired
    private ProductRepository productRepository;


    public void validate(BidDto bidDto, BindingResult bindingResult, String productId) {
        String bidValue = bidDto.getValue();
        // 1.sa nu fie gol
        //  2.sa fie numar pozitiv
        //  4.sa fie mai mare ca ultimul bid
        //  3.sa fie mai mare sau egal  ca starting price
        //
        if (bidValue.isEmpty()) {
            FieldError fieldError = new FieldError("bidDto", "value", "Value should not be empty!");
            bindingResult.addError(fieldError);
            return;
        }

        Integer bidValueAsNumber = 0;
        try {
            bidValueAsNumber = Integer.valueOf(bidValue);


        } catch (NumberFormatException numberFormatException) {
            FieldError fieldError = new FieldError("bidDto", "value", "Value should be number!");
            bindingResult.addError(fieldError);
            return;
        }

        if (bidValueAsNumber <= 0) {
            FieldError fieldError = new FieldError("bidDto", "value", "Value should be positive!");
            bindingResult.addError(fieldError);
            return;
        }
        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        if (!optionalProduct.isPresent()) {
            FieldError fieldError = new FieldError("bidDto", "value", "Product id is not valid");
            bindingResult.addError(fieldError);
            return;
        }

        Product product = optionalProduct.get();
        if (product.getStartBiddingPrice() > bidValueAsNumber) {
            FieldError fieldError = new FieldError("bidDto", "value", "Value must be at least starting price! ");
            bindingResult.addError(fieldError);
            return;
        }

        List<Bid> bidList = product.getBidsList();
        if (!bidList.isEmpty()) {
            int maxBid = 0;
            for (Bid bid : bidList) {
                if (maxBid < bid.getValue()) {
                    maxBid = bid.getValue();
                }
            }
            if (bidValueAsNumber <= maxBid) {
                FieldError fieldError = new FieldError("bidDto", "value", "Value must be greater than latest bid! ");
                bindingResult.addError(fieldError);
                return;
            }
        }
    }
}

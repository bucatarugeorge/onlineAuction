package com.sda.onlineAuction.mapper;

import com.sda.onlineAuction.dto.BidDto;
import com.sda.onlineAuction.model.Bid;
import com.sda.onlineAuction.model.Product;
import com.sda.onlineAuction.model.User;
import com.sda.onlineAuction.repository.ProductRepository;
import com.sda.onlineAuction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidMapper {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    public Bid map(BidDto bidDto,String productId, String userEmail){
        Optional<Product> optionalProduct= productRepository.findById(Integer.valueOf(productId));
        if(!optionalProduct.isPresent()){
            throw new RuntimeException("Product id is not valid");
        }
        Optional<User> optionalUser= userRepository.findByEmail(userEmail);
        if(!optionalUser.isPresent()){
            throw new RuntimeException("User with email is not valid");
        }
        Bid bid = new Bid();
        bid.setValue(Integer.valueOf(bidDto.getValue()));
        bid.setProduct(optionalProduct.get());
        bid.setUser(optionalUser.get());
        return bid;

    }

}

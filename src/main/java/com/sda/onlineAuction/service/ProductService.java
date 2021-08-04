package com.sda.onlineAuction.service;

import com.sda.onlineAuction.dto.ProductDto;
import com.sda.onlineAuction.model.Product;
import com.sda.onlineAuction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService{
    @Autowired
    private ProductRepository productRepository;

    private Product product;

    public void add(ProductDto productDto){
    Product product = new Product();
    product.setName(productDto.getName());
    product.setDescription(productDto.getDescription());
    product.setStartBiddingPrice(Integer.valueOf(productDto.getStartBiddingPrice()));
    product.setEndDateTime(LocalDateTime.parse(productDto.getEndDateTime()));
    productRepository.save(product);
    }

}

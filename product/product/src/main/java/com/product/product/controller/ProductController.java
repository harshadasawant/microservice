package com.product.product.controller;

import com.product.product.dto.Product;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") String productId){
      Map<String,String> productMap = Stream.of(new Product("1234","product1"),
               new Product("5678","product2")).
                collect(Collectors.toMap(Product::getProductId,Product::getName));
        System.out.println("========="+productId);
        System.out.println("==============="+productMap.get(productId));
        return new Product(productId, productMap.get(productId));
    }
}

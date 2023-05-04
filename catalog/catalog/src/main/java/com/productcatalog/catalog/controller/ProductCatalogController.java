package com.productcatalog.catalog.controller;

import com.productcatalog.catalog.dto.CatalogItem;
import com.productcatalog.catalog.dto.Product;
import com.productcatalog.catalog.dto.Rating;
import com.productcatalog.catalog.dto.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/catalog")
public class ProductCatalogController {
    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
//        WebClient.Builder builder= WebClient.builder();

UserRating userRating = restTemplate.getForObject("http://rating-service/ratings/users/"+userId, UserRating.class);
        List<Rating> ratings =userRating.getUserRating();
    return ratings.stream().map(rating->{
        System.out.println(rating.getProductId());
    String url = "http://product-service/product/"+rating.getProductId();
    Product product = restTemplate.getForObject(url,Product.class);
//        Product product = webClientBuilder.build().get().uri(url).retrieve().bodyToMono(Product.class).block();
    return new CatalogItem(product.getName(),"Description",rating.getRatigs());

        }).collect(Collectors.toList());
//    List<CatalogItem> catalogItemList= Stream.of(new CatalogItem("CatalogItem","description",2), new CatalogItem("CatalogItem1","Description2",3)).collect(Collectors.toList());
//        return catalogItemList;

    }
}

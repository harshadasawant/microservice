package com.rating.rating.controller;

import com.rating.rating.dto.Rating;
import com.rating.rating.dto.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @GetMapping("/{productId}")
    public Rating getRating(@PathVariable("productId") String productId){
        return new Rating(productId, 4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(new Rating("1234",4), new Rating("5678",5));
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;

    }

}

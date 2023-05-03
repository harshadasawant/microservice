package com.rating.rating.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRating {
    List<Rating> userRating;
}

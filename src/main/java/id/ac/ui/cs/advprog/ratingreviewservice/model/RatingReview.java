package id.ac.ui.cs.advprog.ratingreviewservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RatingReview{
    private String productId;
    private int rating;
    private String review;
}
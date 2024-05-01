package id.ac.ui.cs.advprog.ratingreviewservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class RatingReview{
    private String boxId;
    private String ratingReviewId;
    private int rating;
    private String review;

    public RatingReview() {
        this.ratingReviewId = UUID.randomUUID().toString();
    }
}
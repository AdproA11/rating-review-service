package id.ac.ui.cs.advprog.ratingreviewservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class RatingReview{
    private long subscriptionId;
    private String ratingReviewId;
    private String ownerName;
    private int rating;
    private String review;

    public RatingReview() {
        this.ratingReviewId = UUID.randomUUID().toString();
    }
}
package id.ac.ui.cs.advprog.ratingreviewservice.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class UserReview {
    String userReviewId;
    List<RatingReview> ratingReviews;
    Long lastEditedTime;
    String author;
    @Setter
    String status;

    public UserReview(String userReviewId, List<RatingReview> ratingReviews, Long lastEditedTime, String author) {
    }

    public UserReview(String userReviewId, List<RatingReview> ratingReviews, Long lastEditedTime, String author, String status) {
    }
}
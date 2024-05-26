package id.ac.ui.cs.advprog.ratingreviewservice.model;

import id.ac.ui.cs.advprog.ratingreviewservice.enums.UserReviewStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Builder
@Getter
public class UserReview {
    String userReviewId;
    List<RatingReview> ratingReviews;
    Long lastEditedTime;
    String author;
    String status;

    public UserReview(String userReviewId, List<RatingReview> ratingReviews, Long lastEditedTime, String author) {
        this.userReviewId = userReviewId;
        this.lastEditedTime = lastEditedTime;
        this.author = author;
        this.status = UserReviewStatus.PENDING.getValue();

        if (ratingReviews.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.ratingReviews = ratingReviews;
        }
    }

    public UserReview(String userReviewId, List<RatingReview> ratingReviews, Long lastEditedTime, String author, String status) {
        this(userReviewId, ratingReviews, lastEditedTime, author);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (UserReviewStatus.contains(status)){
            this.status = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
package id.ac.ui.cs.advprog.ratingreviewservice.model;

import java.util.List;
import java.util.Arrays;

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
        this.userReviewId = userReviewId;
        this.lastEditedTime = lastEditedTime;
        this.author = author;
        this.status = "PENDING";

        if (ratingReviews.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.ratingReviews = ratingReviews;
        }
    }

    public UserReview(String userReviewId, List<RatingReview> ratingReviews, Long lastEditedTime, String author, String status) {
        this(userReviewId, ratingReviews, lastEditedTime, author);

        String[] statusList = {"PENDING", "APPROVED", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

    public void setStatus(String status) {
        String[] statusList = {"PENDING", "APPROVED", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }
}
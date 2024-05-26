package id.ac.ui.cs.advprog.ratingreviewservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UserReviewTest {
    private List<RatingReview> ratingReviews;
    @BeforeEach
    void setUp() {
        this.ratingReviews = new ArrayList<>();
        RatingReview ratingReview1 = new RatingReview();
        ratingReview1.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview1.setOwnerName("Test 1");
        ratingReview1.setRating(2);
        ratingReview1.setReview("Gak worth it");
        this.ratingReviews.add(ratingReview1);
    }

    @Test
    void testCreateUserReviewEmptyRatingReview() {
        this.ratingReviews.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            UserReview userReview = new UserReview("13652556-012a-4c07-b546-54eb1396d79b",
                    this.ratingReviews, 1708560000L, "Burhan");
        });
    }

    @Test
    void testCreateUserReviewPendingStatus() {
        UserReview userReview = new UserReview ("13652556-012a-4c07-b546-54eb1396d79b",
                this.ratingReviews, 1708560000L, "Burhan");

        assertSame(this. ratingReviews, userReview.getRatingReviews());
        assertEquals(1, userReview.getRatingReviews().size());
        assertEquals("Gak worth it", userReview.getRatingReviews().get(0).getReview());
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", userReview.getUserReviewId());
        assertEquals(1708560000L, userReview.getLastEditedTime());
        assertEquals("Burhan", userReview.getAuthor());
        assertEquals("PENDING", userReview.getStatus());
    }

    @Test
    void testCreateUserReviewInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            UserReview userReview = new UserReview("13652556-012a-4c07-b546-54eb1396d79b",
                    this.ratingReviews, 1708560000L, "Burhan", "BANNED");
        });
    }

    @Test
    void testCreateUserReviewApprovedStatus() {
        UserReview userReview = new UserReview ("13652556-012a-4c07-b546-54eb1396d79b",
                this.ratingReviews, 1708568000L, "Burhan", "APPROVED");
        userReview.setStatus("APPROVED");
        assertEquals("APPROVED", userReview.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        UserReview userReview = new UserReview ("13652556-012a-4c07-b546-54eb1396d79b",
                this.ratingReviews, 1788568000L, "Burhan", "PENDING");
        userReview.setStatus("REJECTED");
        assertEquals("REJECTED", userReview.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        UserReview userReview = new UserReview ("13652556-012a-4c07-b546-54eb1396d79b",
                this.ratingReviews, 1788568000L, "Burhan");
        assertThrows(IllegalArgumentException.class, () -> userReview.setStatus("BANNED"));
    }
}

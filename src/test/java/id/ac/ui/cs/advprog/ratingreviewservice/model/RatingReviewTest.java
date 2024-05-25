package id.ac.ui.cs.advprog.ratingreviewservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class RatingReviewTest {
    RatingReview ratingReview;

    @BeforeEach
    void setUp() {
        this.ratingReview = new RatingReview();
        this.ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.ratingReview.setOwnerName("Burhan");
        this.ratingReview.setRating(5);
        this.ratingReview.setReview("Box-nya sangat worth it! Gak nyesel langganan di sini.");
    }

    @Test
    void testGetRatingReviewId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.ratingReview.getRatingReviewId());
    }

    @Test
    void testGetOwnerName() {
        assertEquals("Burhan", this.ratingReview.getOwnerName());
    }

    @Test
    void testGetRating() {
        assertEquals(5, this.ratingReview.getRating());
    }

    @Test
    void testGetReview() {
        assertEquals("Box-nya sangat worth it! Gak nyesel langganan di sini.", this.ratingReview.getReview());
    }
}
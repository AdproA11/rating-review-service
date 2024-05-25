package id.ac.ui.cs.advprog.ratingreviewservice.repository;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RatingReviewRepositoryTest {

    @InjectMocks
    RatingReviewRepository ratingReviewRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("Adhan");
        ratingReview.setRating(5);
        ratingReview.setReview("Box-nya sangat worth it! Gak nyesel langganan di sini.");
        ratingReviewRepository.create(ratingReview);

        Iterator<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        assertTrue(ratingReviewIterator.hasNext());

        RatingReview savedRatingReview = ratingReviewIterator.next();
        assertEquals(ratingReview.getRatingReviewId(), savedRatingReview.getRatingReviewId());
        assertEquals(ratingReview.getOwnerName(), savedRatingReview.getOwnerName());
        assertEquals(ratingReview.getRating(), savedRatingReview.getRating());
        assertEquals(ratingReview.getReview(), savedRatingReview.getReview());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        assertFalse(ratingReviewIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneRatingReview() {
        RatingReview ratingReview1 = new RatingReview();
        ratingReview1.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview1.setOwnerName("Burhan");
        ratingReview1.setRating(5);
        ratingReview1.setReview("Box-nya sangat worth it! Gak nyesel langganan di sini.");
        ratingReviewRepository.create(ratingReview1);

        RatingReview ratingReview2 = new RatingReview();
        ratingReview2.setRatingReviewId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        ratingReview2.setOwnerName("Sofita");
        ratingReview2.setRating(3);
        ratingReview2.setReview("Di bawah ekspetasi...");
        ratingReviewRepository.create(ratingReview2);

        Iterator<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        assertTrue(ratingReviewIterator.hasNext());

        RatingReview savedRatingreview = ratingReviewIterator.next();
        assertEquals(ratingReview1.getRatingReviewId(), savedRatingreview.getRatingReviewId());
        savedRatingreview = ratingReviewIterator.next();
        assertEquals(ratingReview2.getRatingReviewId(), savedRatingreview.getRatingReviewId());
        assertFalse(ratingReviewIterator.hasNext());
    }

    @Test
    void testEditRatingReview() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("Adhan");
        ratingReview.setRating(5);
        ratingReview.setReview("Box-nya sangat worth it! Gak nyesel langganan di sini.");
        ratingReviewRepository.create(ratingReview);

        RatingReview updatedRatingReview = new RatingReview();
        updatedRatingReview.setRating(3);
        updatedRatingReview.setReview("Ternyata cepet rusak...");
        ratingReviewRepository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedRatingReview);

        RatingReview retrievedRatingReview = ratingReviewRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(retrievedRatingReview);
        assertEquals(3, retrievedRatingReview.getRating());
        assertEquals("Ternyata cepet rusak...", retrievedRatingReview.getReview());
    }

    @Test
    void testDeleteRatingReview() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("Adhan");
        ratingReview.setRating(5);
        ratingReview.setReview("Box-nya sangat worth it! Gak nyesel langganan di sini.");
        ratingReviewRepository.create(ratingReview);

        ratingReviewRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        RatingReview retrievedRatingReview = ratingReviewRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(retrievedRatingReview);
    }

    @Test
    void testEditRatingReviewNonExistent() {
        RatingReview updatedRatingReview = new RatingReview();
        updatedRatingReview.setRatingReviewId("nonexistent-id");
        updatedRatingReview.setOwnerName("Adhan");
        updatedRatingReview.setRating(5);
        updatedRatingReview.setReview("Box-nya sangat worth it! Gak nyesel langganan di sini.");

        assertDoesNotThrow(() -> ratingReviewRepository.update("nonexistent-id", updatedRatingReview));
    }

    @Test
    void testDeleteProductNonExistent() {
        assertDoesNotThrow(() -> ratingReviewRepository.delete("nonexistent-id"));
    }
}
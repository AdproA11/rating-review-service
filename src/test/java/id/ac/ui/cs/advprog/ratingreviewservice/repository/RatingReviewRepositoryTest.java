package id.ac.ui.cs.advprog.ratingreviewservice.repository;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RatingReviewRepositoryTest {

    @InjectMocks
    RatingReviewRepository ratingReviewRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("Sampo Cap Bambang");
        ratingReview.setRating(100);
        ratingReviewRepository.create(ratingReview);

        Iterator<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        assertTrue(ratingReviewIterator.hasNext());

        RatingReview savedRatingReview = ratingReviewIterator.next();
        assertEquals(ratingReview.getRatingReviewId(), savedRatingReview.getRatingReviewId());
        assertEquals(ratingReview.getOwnerName(), savedRatingReview.getOwnerName());
        assertEquals(ratingReview.getRating(), savedRatingReview.getRating());
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
        ratingReview1.setOwnerName("Sampo Cap Bambang");
        ratingReview1.setRating(100);
        ratingReviewRepository.create(ratingReview1);

        RatingReview ratingReview2 = new RatingReview();
        ratingReview2.setRatingReviewId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        ratingReview2.setOwnerName("Sampo Cap Usep");
        ratingReview2.setRating(200);
        ratingReviewRepository.create(ratingReview2);

        Iterator<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        assertTrue(ratingReviewIterator.hasNext());

        RatingReview savedRatingReview = ratingReviewIterator.next();
        assertEquals(ratingReview1.getRatingReviewId(), savedRatingReview.getRatingReviewId());
        savedRatingReview = ratingReviewIterator.next();
        assertEquals(ratingReview2.getRatingReviewId(), savedRatingReview.getRatingReviewId());
        assertFalse(ratingReviewIterator.hasNext());
    }

    @Test
    void testFindByIdRatingReview() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setOwnerName("Sampo Cap Bambang");
        ratingReview.setRating(100);
        ratingReviewRepository.create(ratingReview);

        RatingReview foundRatingReview = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertEquals(ratingReview.getRatingReviewId(), foundRatingReview.getRatingReviewId());
        assertEquals(ratingReview.getOwnerName(), foundRatingReview.getOwnerName());
        assertEquals(ratingReview.getRating(), foundRatingReview.getRating());
    }

    @Test
    void testEditRatingReview() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("RatingReview 1");
        ratingReview.setRating(5);
        ratingReview.setReview("Bagus banget!");
        ratingReviewRepository.create(ratingReview);

        RatingReview updatedRatingReview = new RatingReview();
        updatedRatingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedRatingReview.setRating(3);
        updatedRatingReview.setReview("Ternyata cepet rusak...");
        ratingReviewRepository.update(updatedRatingReview);

        RatingReview retrievedRatingReview = ratingReviewRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(retrievedRatingReview);
        assertEquals(3, retrievedRatingReview.getRating());
        assertEquals("Ternyata cepet rusak...", retrievedRatingReview.getReview());
    }

    @Test
    void testFindByIdRatingReviewIfDoesNotExist() {
        RatingReview ratingReview1 = new RatingReview();
        ratingReview1.setOwnerName("RatingReview 1");
        ratingReview1.setRating(100);
        ratingReviewRepository.create(ratingReview1);

        RatingReview ratingReview2 = new RatingReview();
        ratingReview2.setOwnerName("RatingReview 2");
        ratingReview2.setRating(200);
        ratingReviewRepository.create(ratingReview2);

        String randomId = UUID.randomUUID().toString();

        RatingReview findedRatingReview = ratingReviewRepository.findById(randomId);
        assertNull(findedRatingReview);
    }

    @Test
    void testFindByIdRatingReviewIfMoreThanOneRatingReview() {
        for (int i = 0; i < 5; i++) {
            RatingReview ratingReview = new RatingReview();
            ratingReview.setOwnerName(String.format("RatingReview %d", i + 1));
            ratingReview.setRating(100 + i);
            ratingReviewRepository.create(ratingReview);
        }

        RatingReview lastRatingReview = new RatingReview();
        lastRatingReview.setOwnerName("Last RatingReview");
        lastRatingReview.setRating(130);
        ratingReviewRepository.create(lastRatingReview);

        RatingReview foundRatingReview = ratingReviewRepository.findById(lastRatingReview.getRatingReviewId());
        assertEquals(lastRatingReview.getRatingReviewId(), foundRatingReview.getRatingReviewId());
        assertEquals(lastRatingReview.getOwnerName(), foundRatingReview.getOwnerName());
        assertEquals(lastRatingReview.getRating(), foundRatingReview.getRating());
    }

    @Test
    void testEditAndFindByIdRatingReview() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("RatingReview 1");
        ratingReview.setRating(4);
        ratingReview.setReview("Sebelum");
        ratingReviewRepository.create(ratingReview);

        RatingReview findRatingReviewById = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertEquals(findRatingReviewById.getRatingReviewId(), ratingReview.getRatingReviewId());
        assertEquals(findRatingReviewById.getOwnerName(), ratingReview.getOwnerName());
        assertEquals(findRatingReviewById.getRating(), ratingReview.getRating());

        RatingReview editRatingReviewData = new RatingReview();
        editRatingReviewData.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editRatingReviewData.setRating(5);
        editRatingReviewData.setReview("Sesudah");
        ratingReviewRepository.update(editRatingReviewData);

        RatingReview editedRatingReview = ratingReviewRepository.findById(editRatingReviewData.getRatingReviewId());
        assertEquals(editRatingReviewData.getRatingReviewId(), editedRatingReview.getRatingReviewId());
        assertEquals(5, editedRatingReview.getRating());
        assertEquals("Sesudah", editedRatingReview.getReview());
    }

    @Test
    void testEditWithValidIdAndNot() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("RatingReview 1");
        ratingReview.setRating(5);
        ratingReview.setReview("Test sebelum edit");
        ratingReviewRepository.create(ratingReview);

        RatingReview editRatingReviewData = new RatingReview();
        editRatingReviewData.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editRatingReviewData.setRating(4);
        editRatingReviewData.setReview("Test setelah edit");
        ratingReviewRepository.update(editRatingReviewData);

        RatingReview editedRatingReview = ratingReviewRepository.findById(editRatingReviewData.getRatingReviewId());
        assertEquals(editRatingReviewData.getRatingReviewId(), editedRatingReview.getRatingReviewId());
        assertEquals(4, editedRatingReview.getRating());

        String randomId = UUID.randomUUID().toString();

        RatingReview newRatingReview = new RatingReview();
        newRatingReview.setRatingReviewId(randomId);
        newRatingReview.setOwnerName("New RatingReview");
        newRatingReview.setRating(100);

        RatingReview foundedRatingReview = ratingReviewRepository.update(newRatingReview);
        assertNull(foundedRatingReview);
    }

    @Test
    void testEditRatingReviewIfRatingPositive() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("RatingReview 1");
        ratingReview.setRating(2);
        ratingReview.setReview("Before");
        ratingReviewRepository.create(ratingReview);

        RatingReview findRatingReviewById = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertEquals(findRatingReviewById.getRatingReviewId(), ratingReview.getRatingReviewId());
        assertEquals(findRatingReviewById.getOwnerName(), ratingReview.getOwnerName());
        assertEquals(findRatingReviewById.getRating(), ratingReview.getRating());

        RatingReview editRatingReviewData = new RatingReview();
        editRatingReviewData.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editRatingReviewData.setRating(5);
        editRatingReviewData.setReview("After");
        ratingReviewRepository.update(editRatingReviewData);

        RatingReview editedRatingReview = ratingReviewRepository.findById(editRatingReviewData.getRatingReviewId());
        assertEquals(editRatingReviewData.getRatingReviewId(), editedRatingReview.getRatingReviewId());
        assertEquals(5, editedRatingReview.getRating());
        assertEquals("After", editedRatingReview.getReview());
    }

    @Test
    void testEditRatingReviewIfRatingNegative() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("RatingReview 1");
        ratingReview.setRating(5);
        ratingReview.setReview("Test kalo rating negatif");
        ratingReviewRepository.create(ratingReview);

        RatingReview findRatingReviewById = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertEquals(findRatingReviewById.getRatingReviewId(), ratingReview.getRatingReviewId());
        assertEquals(findRatingReviewById.getOwnerName(), ratingReview.getOwnerName());
        assertEquals(findRatingReviewById.getRating(), ratingReview.getRating());
        assertEquals(findRatingReviewById.getReview(), ratingReview.getReview());

        RatingReview editRatingReviewData = new RatingReview();
        editRatingReviewData.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editRatingReviewData.setRating(-10);
        editRatingReviewData.setReview("Harusnya jadi nol");
        ratingReviewRepository.update(editRatingReviewData);

        RatingReview editedRatingReview = ratingReviewRepository.findById(editRatingReviewData.getRatingReviewId());
        assertEquals(editRatingReviewData.getRatingReviewId(), editedRatingReview.getRatingReviewId());
        assertEquals(0, editedRatingReview.getRating());
        assertEquals("Harusnya jadi nol", editedRatingReview.getReview());
    }

    @Test
    void testDeleteAndFindByIdRatingReview() {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setOwnerName("RatingReview 1");
        ratingReview.setRating(100);
        ratingReviewRepository.create(ratingReview);

        RatingReview deletedRatingReview = ratingReviewRepository.delete(ratingReview.getRatingReviewId());
        assertEquals(ratingReview.getRatingReviewId(), deletedRatingReview.getRatingReviewId());
        assertEquals(ratingReview.getOwnerName(), deletedRatingReview.getOwnerName());
        assertEquals(ratingReview.getRating(), deletedRatingReview.getRating());

        RatingReview deletedRatingReviewIfSearch = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertNull(deletedRatingReviewIfSearch);
    }

    @Test
    void testDeleteRatingReviewIfEmpty() {
        String randomId = UUID.randomUUID().toString();

        RatingReview deletedRatingReview = ratingReviewRepository.delete(randomId);
        assertNull(deletedRatingReview);
    }

    @Test
    void testDeleteRatingReviewIfDoesNotExist() {
        RatingReview ratingReview1 = new RatingReview();
        ratingReview1.setOwnerName("RatingReview 1");
        ratingReview1.setRating(100);
        ratingReviewRepository.create(ratingReview1);

        RatingReview ratingReview2 = new RatingReview();
        ratingReview2.setOwnerName("RatingReview 2");
        ratingReview2.setRating(200);
        ratingReviewRepository.create(ratingReview2);

        String randomId = UUID.randomUUID().toString();

        RatingReview findedRatingReview = ratingReviewRepository.delete(randomId);
        assertNull(findedRatingReview);
    }
}

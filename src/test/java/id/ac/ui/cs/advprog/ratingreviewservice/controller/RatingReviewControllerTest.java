package id.ac.ui.cs.advprog.ratingreviewservice.controller;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import id.ac.ui.cs.advprog.ratingreviewservice.service.RatingReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Maaf lupa membedakan commit message antara menambah testing atau fixing to improve code coverage
@ExtendWith(MockitoExtension.class)
class RatingReviewControllerTest {

    @InjectMocks
    private RatingReviewController ratingReviewController;

    @Mock
    private RatingReviewService ratingReviewService;

    @Mock
    private Model model;

    private RatingReview ratingReview;

    @BeforeEach
    void setUp() {
        ratingReview = new RatingReview();
        ratingReview.setRatingReviewId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ratingReview.setOwnerName("A11");
        ratingReview.setRating(4);
        ratingReview.setReview("Mantab sekali");
    }

    @Test
    void testAddRatingReviewPage() {
        String viewName = ratingReviewController.addRatingReviewPage(model);

        assertEquals("AddRatingReview", viewName);
        verify(model).addAttribute(eq("ratingReview"), any(RatingReview.class));
    }

    @Test
    void testAddRatingReviewPost() {
        String redirectURL = ratingReviewController.addRatingReviewPost(ratingReview, model);

        assertEquals("redirect:/rating-review/list", redirectURL);
        verify(ratingReviewService).create(ratingReview);
    }

    @Test
    void testRatingReviewListPage() {
        List<RatingReview> ratingReviewList = new ArrayList<>();
        ratingReviewList.add(ratingReview);
        when(ratingReviewService.findAll()).thenReturn(ratingReviewList);

        String viewName = ratingReviewController.ratingReviewListPage(model);

        assertEquals("RatingReviewList", viewName);
        verify(model).addAttribute("ratingReviews", ratingReviewList);
    }

    @Test
    void testEditRatingReviewPage() {
        String ratingReviewId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(ratingReviewService.findById(ratingReviewId)).thenReturn(ratingReview);

        String viewName = ratingReviewController.editRatingReviewPage(model, ratingReviewId);

        assertEquals("EditRatingReview", viewName);
        verify(model).addAttribute("ratingReview", ratingReview);
    }

    @Test
    void testEditRatingReviewPost() {
        String redirectURL = ratingReviewController.editRatingReviewPut(ratingReview);

        assertEquals("redirect:list", redirectURL);
        verify(ratingReviewService).update(ratingReview);
    }

    @Test
    void testDeleteProduct() {
        String ratingReviewId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        String redirectURL = ratingReviewController.deleteRatingReview(ratingReviewId);

        assertEquals("redirect:../list", redirectURL);
        verify(ratingReviewService).delete(ratingReviewId);
    }
}
package id.ac.ui.cs.advprog.ratingreviewservice.service;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import java.util.List;

public interface RatingReviewService{
    public RatingReview create(RatingReview ratingReview);
    public List<RatingReview> findAll();
    public RatingReview findById(String id);
    public void update(String id, RatingReview ratingReview);
    public void delete(String id);
}
package id.ac.ui.cs.advprog.ratingreviewservice.repository;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class RatingReviewRepository{
    private List<RatingReview> ratingReviewData = new ArrayList<>();

    public RatingReview create(RatingReview ratingReview){
        ratingReviewData.add(ratingReview);
        return ratingReview;
    }

    public Iterator<RatingReview> findAll(){
        return ratingReviewData.iterator();
    }

    public RatingReview findById(String id) {
        for (RatingReview ratingReview : ratingReviewData) {
            if (ratingReview.getRatingReviewId().equals(id)) {
                return ratingReview;
            }
        }
        return null;
    }

    public void update(String id, RatingReview updateRatingReview) {
        for (RatingReview ratingReview : ratingReviewData) {
            if (ratingReview.getRatingReviewId().equals(id)) {
                ratingReview.setRating(updateRatingReview.getRating());
                ratingReview.setReview(updateRatingReview.getReview());
                return;
            }
        }
    }

    public void delete(String id) {
        RatingReview ratingReview = findById(id);
        ratingReviewData.remove(ratingReview);
    }
}
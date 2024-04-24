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
}
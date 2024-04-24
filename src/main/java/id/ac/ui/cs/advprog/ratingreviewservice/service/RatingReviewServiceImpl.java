package id.ac.ui.cs.advprog.ratingreviewservice.service;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import id.ac.ui.cs.advprog.ratingreviewservice.repository.RatingReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RatingReviewServiceImpl implements RatingReviewService {
    @Autowired
    private RatingReviewRepository ratingReviewRepository;

    @Override
    public RatingReview create(RatingReview ratingReview){
        ratingReviewRepository.create(ratingReview);
        return ratingReview;
    }

    @Override
    public List<RatingReview> findAll(){
        Iterator<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        List<RatingReview> allRatingReview = new ArrayList<>();
        ratingReviewIterator.forEachRemaining(allRatingReview::add);
        return allRatingReview;
    }
}
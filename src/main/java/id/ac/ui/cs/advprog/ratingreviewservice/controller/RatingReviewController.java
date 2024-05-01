package id.ac.ui.cs.advprog.ratingreviewservice.controller;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import id.ac.ui.cs.advprog.ratingreviewservice.service.RatingReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rating-review")
public class RatingReviewController {

    @Autowired
    private RatingReviewService service;

    @GetMapping("/add")
    public String addRatingReviewPage(Model model) {
        RatingReview ratingReview = new RatingReview();
        model.addAttribute("ratingReview", ratingReview);
        return "addRatingReview";
    }

    @PostMapping("/add")
    public String addRatingReviewPost(@ModelAttribute RatingReview ratingReview, Model model) {
        service.create(ratingReview);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String ratingReviewListPage(Model model) {
        List<RatingReview> allRatingReview = service.findAll();
        model.addAttribute("reviewBox", allRatingReview);
        return "listRatingReview";
    }
}
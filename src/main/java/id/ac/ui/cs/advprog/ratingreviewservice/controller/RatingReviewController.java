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
        return "AddRatingReview";
    }

    @PostMapping("/add")
    public String addRatingReview(@ModelAttribute RatingReview ratingReview, Model model) {
        service.create(ratingReview);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String ratingReviewListPage(Model model) {
        List<RatingReview> allRatingReview = service.findAll();
        model.addAttribute("ratingReviews", allRatingReview);
        return "RatingReviewList";
    }

    @GetMapping("/edit/{id}")
    public String editRatingReviewPage(Model model, @PathVariable String id) {
        RatingReview ratingReview = service.findById(id);
        model.addAttribute("ratingReview", ratingReview);
        return "EditRatingReview";
    }

    @PostMapping("/edit/{id}")
    public String editRatingReviewPost(@ModelAttribute RatingReview ratingReview, @PathVariable String id) {
        service.update(id, ratingReview);
        return "redirect:/rating-review/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRatingReview(@PathVariable("id") String id) {
        service.delete(id);
        return "redirect:/rating-review/list";
    }
}
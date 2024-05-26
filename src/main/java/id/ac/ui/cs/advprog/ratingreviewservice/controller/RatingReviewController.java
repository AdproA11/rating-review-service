package id.ac.ui.cs.advprog.ratingreviewservice.controller;

import id.ac.ui.cs.advprog.ratingreviewservice.model.RatingReview;
import id.ac.ui.cs.advprog.ratingreviewservice.service.RatingReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import java.util.*;

@Controller
@RequestMapping("/rating-review")
public class RatingReviewController {

    private static final String BOX_API_URL = "http://34.126.126.9/api/subscription-box/all";

    @Autowired
    private RatingReviewService service;

    @GetMapping("/api/rating-review/all")
    public List<RatingReview> getAllRatingReviews() {
        Iterator<RatingReview> iterator = service.findAll();
        List<RatingReview> ratingReviews = new ArrayList<>();
        iterator.forEachRemaining(ratingReviews::add);
        return ratingReviews;
    }

    @GetMapping("/add/{boxId}")
    public String addRatingReviewPage(@PathVariable("boxId") Long boxId, Model model) {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setBoxId(boxId); // Set boxId
        model.addAttribute("ratingReview", ratingReview);
        model.addAttribute("boxId", boxId); // Add boxId to model
        return "AddRatingReview";
    }

    @PostMapping("/add/{boxId}")
    public String addRatingReviewPost(@ModelAttribute RatingReview ratingReview, @PathVariable("boxId") Long boxId, Model model) {
        ratingReview.setBoxId(boxId);
        service.create(ratingReview);
        return "redirect:/rating-review/list/"+ boxId;
    }

    @GetMapping("/list")
    public String ratingReviewListPage(Model model) {
        List<RatingReview> allRatingReview = service.findAll();
        model.addAttribute("ratingReviews", allRatingReview);
        return "RatingReviewList";
    }

    @GetMapping("/list/{boxId}")
    public String ratingReviewListPage(@PathVariable("boxId") Long boxId, Model model) {
        List<RatingReview> allRatingReview = service.findAll();
        List<RatingReview> ratingReviewsWithBoxId = new ArrayList<>();

        for (RatingReview ratingReview : allRatingReview) {
            if (ratingReview.getBoxId() == boxId) {
                ratingReviewsWithBoxId.add(ratingReview);
            }
        }

        model.addAttribute("ratingReviews", ratingReviewsWithBoxId);
        model.addAttribute("boxId", boxId);

        return "RatingReviewList";
    }

    @GetMapping("/edit/{ratingReviewId}")
    public String editRatingReviewPage(Model model, @PathVariable("ratingReviewId") String ratingReviewId) {
        RatingReview ratingReview = service.findById(ratingReviewId);
        model.addAttribute("ratingReview", ratingReview);
        return "EditRatingReview";
    }

    @PutMapping("/edit")
    public String editRatingReviewPut(@ModelAttribute RatingReview ratingReview) {
        service.update(ratingReview);
        return "redirect:list";
    }

    @DeleteMapping("/delete/{ratingReviewId}")
    public String deleteRatingReview(@PathVariable("ratingReviewId") String ratingReviewId) {
        service.delete(ratingReviewId);
        return "redirect:../list";
    }

    @GetMapping("/list-box")
    public String listBoxPage(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(BOX_API_URL, Object.class);
        Object responseBody = responseEntity.getBody();

        model.addAttribute("subscriptionBoxes", responseBody);
        return "BoxList";
    }
}
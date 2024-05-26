package id.ac.ui.cs.advprog.ratingreviewservice.enums;

import id.ac.ui.cs.advprog.ratingreviewservice.model.UserReview;
import lombok.Getter;


@Getter
public enum UserReviewStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String value;

    private UserReviewStatus(String value){
        this.value = value;
    }

    public static boolean contains(String param){
        for (UserReviewStatus userReviewStatus: UserReviewStatus.values()){
            if(userReviewStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}
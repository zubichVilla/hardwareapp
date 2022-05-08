package hr.tvz.zubcic.hardwareapp.review;

public class ReviewDTO {

    private final String reviewText;

    private final Review.Grade grade;

    public ReviewDTO(String reviewText, Review.Grade grade) {
        this.reviewText = reviewText;
        this.grade = grade;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Review.Grade getGrade() {
        return grade;
    }
}

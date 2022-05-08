package hr.tvz.zubcic.hardwareapp.review;

public class Review {

    private Long id;

    private String reviewText;

    private Grade grade;

    public enum Grade{
        ONE, TWO, THREE, FOUR, FIVE
    }

    public Review() {
    }

    public Review(Long id, String reviewText, Grade grade) {
        this.id = id;
        this.reviewText = reviewText;
        this.grade = grade;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Grade getGrade() {
        return grade;
    }
}

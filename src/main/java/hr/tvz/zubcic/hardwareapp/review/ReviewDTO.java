package hr.tvz.zubcic.hardwareapp.review;

public class ReviewDTO {

    private final String title;

    private final String reviewText;

//    private final Review.Grade grade;
    private final Integer grade;

    public ReviewDTO(String title, String reviewText, Integer grade) {
        this.title = title;
        this.reviewText = reviewText;
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public String getReviewText() {
        return reviewText;
    }

//    public Review.Grade getGrade() {
//        return grade;
//    }

    public Integer getGrade(){
        return grade;
    }
}

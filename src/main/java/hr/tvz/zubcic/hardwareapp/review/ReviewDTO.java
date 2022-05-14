package hr.tvz.zubcic.hardwareapp.review;

public class ReviewDTO {

    private final Long id;

    private final String title;

    private final String reviewText;

//    private final Review.Grade grade;
    private final Integer grade;

    private final String hardwareCode;

    public ReviewDTO(Long id, String title, String reviewText, Integer grade, String hardwareCode) {
        this.id = id;
        this.title = title;
        this.reviewText = reviewText;
        this.grade = grade;
        this.hardwareCode = hardwareCode;
    }

    public Long getId() {
        return id;
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

    public String getHardwareCode() {
        return hardwareCode;
    }
}

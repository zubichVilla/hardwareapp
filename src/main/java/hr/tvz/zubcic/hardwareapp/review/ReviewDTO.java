package hr.tvz.zubcic.hardwareapp.review;

public class ReviewDTO {

    //private final Long id;

    private final String title;

    private final String text;

//    private final Review.Grade grade;
    private final Integer rating;

    //private final String hardwareCode;

    public ReviewDTO(String title, String text, Integer rating) {
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

//    public Long getId() {
//        return id;
//    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

//    public Review.Grade getGrade() {
//        return grade;
//    }

    public Integer getRating(){
        return rating;
    }

//    public String getHardwareCode() {
//        return hardwareCode;
//    }
}

package hr.tvz.zubcic.hardwareapp.review;

import hr.tvz.zubcic.hardwareapp.hardware.Hardware;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;

    @Column(name = "text")
    private String reviewText;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "hardware_id", nullable = false)
    private Hardware hardware;

    public enum Grade{
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        private final int value;

        private Grade(int value){
            this.value = value;
        }

        public int value() {return this.value;}
    }



    public Review() {
    }

    public Review(Long id, String title, String reviewText, Grade grade) {
        this.id = id;
        this.title = title;
        this.reviewText = reviewText;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getHardwareCode() {
        return hardware.getCode();
    }

    public String getTitle() {
        return title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Grade getGrade() {
        return grade;
    }

}

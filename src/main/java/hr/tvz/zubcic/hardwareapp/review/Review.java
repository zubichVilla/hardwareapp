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

    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "hardware_id", nullable = false)
    private Hardware hardware;

    public enum Rating{
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        private final int value;

        private Rating(int value){
            this.value = value;
        }

        public int value() {return this.value;}
    }



    public Review() {
    }

    public Review(Long id, String title, String reviewText, Rating rating) {
        this.id = id;
        this.title = title;
        this.reviewText = reviewText;
        this.rating = rating;
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

    public Rating getRating() {
        return rating;
    }

    public String getCode(){ return hardware.getCode();}

}

package hr.tvz.zubcic.hardwareapp.web;

import hr.tvz.zubcic.hardwareapp.review.ReviewDTO;
import hr.tvz.zubcic.hardwareapp.review.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping(params = "hardwareCode")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String hardwareCode){
        return reviewService.findAllByHardwareCode(hardwareCode);
    }

    @GetMapping(params = "text")
    public List<ReviewDTO> getAllReviewsWithText(@RequestParam String text){
        return reviewService.findAllReviewsWithText(text);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ReviewDTO> getReviewById(@RequestParam Long id){
        return reviewService.getReviewById(id)
                .map(
                        reviewDTO -> ResponseEntity.status(HttpStatus.OK)
                                .body(reviewDTO)
                )
                .orElseGet(
                        ()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

}

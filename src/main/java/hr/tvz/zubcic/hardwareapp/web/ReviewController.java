package hr.tvz.zubcic.hardwareapp.web;

import hr.tvz.zubcic.hardwareapp.review.ReviewDTO;
import hr.tvz.zubcic.hardwareapp.review.ReviewService;
import hr.tvz.zubcic.hardwareapp.review.ReviewTextDTO;
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

//    @GetMapping
//    public List<ReviewDTO> getAllReviews(){
//        return reviewService.findAll();
//    }

    @GetMapping
    public List<ReviewTextDTO> getAllReviews(){
        return reviewService.findAll();
    }


    @GetMapping(params = "code")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String code){
        return reviewService.findAllByHardwareCode(code);
    }

    @GetMapping(params = "text")
    public List<ReviewTextDTO> getAllReviewsByText(@RequestParam String text){
        return reviewService.findAllByReviewText(text);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ReviewTextDTO> findById(Long id){
        return reviewService.findById(id)
                .map(reviewTextDTO -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(reviewTextDTO))
                .orElseGet(
                        ()->ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

}

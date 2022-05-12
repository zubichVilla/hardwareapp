package hr.tvz.zubcic.hardwareapp.review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


//    @Override
//    public List<ReviewDTO> findAll() {
//        return reviewRepository
//                .findAll()
//                .stream()
//                .map(this::mapReviewToDTO)
//                .collect(Collectors.toList());
//    }
        @Override
    public List<ReviewTextDTO> findAll() {
        return reviewRepository
                .findAll()
                .stream()
                .map(this::mapReviewTextToDTO)
                .collect(Collectors.toList());
    }



    @Override
    public List<ReviewDTO> findAllByHardwareCode(String code) {
        return reviewRepository
                .findAllByHardware_Code(code)
                .stream()
                .map(this::mapReviewToDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO mapReviewToDTO(Review review){
        return new ReviewDTO(review.getTitle(),
                review.getReviewText(),
                review.getGrade().value());
    }


    @Override
    public List<ReviewTextDTO> findAllByReviewText(String text) {
        return reviewRepository
                .findAllByReviewTextContainingIgnoreCase(text)
                .stream()
                .map(this::mapReviewTextToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewTextDTO> findById(Long id) {
        return reviewRepository
                .findById(id)
                .map(this::mapReviewTextToDTO);
    }




    private ReviewTextDTO mapReviewTextToDTO(Review review){
        return new ReviewTextDTO(
                review.getId(),
                review.getTitle(),
                review.getReviewText(),
                review.getGrade().value(),
                review.getHardwareCode());
    }
}

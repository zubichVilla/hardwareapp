package hr.tvz.zubcic.hardwareapp.review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository
                .findAll()
                .stream()
                .map(this::mapReviewToDTO)
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
                review.getGrade());
    }
}

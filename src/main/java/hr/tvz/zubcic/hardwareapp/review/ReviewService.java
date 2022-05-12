package hr.tvz.zubcic.hardwareapp.review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);

    List<ReviewDTO> findAllReviewsWithText(String text);

   Optional<ReviewDTO> getReviewById(Long id);
}

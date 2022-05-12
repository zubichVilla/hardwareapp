package hr.tvz.zubcic.hardwareapp.review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Optional<ReviewTextDTO> findById(Long id);

//    List<ReviewDTO> findAll();

    List<ReviewTextDTO> findAll();

    List<ReviewDTO> findAllByHardwareCode(String code);

    List<ReviewTextDTO> findAllByReviewText(String text);
}

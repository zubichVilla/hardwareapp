package hr.tvz.zubcic.hardwareapp.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByHardware_Code(String code);

    List<Review> findAllByReviewTextContainingIgnoreCase(String code);

    Optional<Review> findById(Long id);

}

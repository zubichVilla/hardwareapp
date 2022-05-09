package hr.tvz.zubcic.hardwareapp.review;

import hr.tvz.zubcic.hardwareapp.hardware.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByHardware_Code(String code);

}

package hr.tvz.zubcic.hardwareapp.hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    List<Hardware> findByType(String type);

}

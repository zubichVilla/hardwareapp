package hr.tvz.zubcic.hardwareapp.hardware;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    List<Hardware> findByRange(BigDecimal lowerRange, BigDecimal upperRange);

    List<Hardware> findByKeyword(String keyword);

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);

    Optional<Hardware> update(String code, Hardware mapCommandToHardware);

    void deleteByCode(String code);

}

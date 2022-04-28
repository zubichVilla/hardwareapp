package hr.tvz.zubcic.hardwareapp.hardware;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    List<HardwareDTO> findByRange(BigDecimal lowerRange, BigDecimal upperRange);

    List<HardwareDTO> findByKeyword(String keyword);

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO>save(HardwareCommand hardwareCommand);

    Optional<HardwareDTO> update(String code, HardwareCommand updatedHardwareCommand);

    void deleteByCode(String code);
}

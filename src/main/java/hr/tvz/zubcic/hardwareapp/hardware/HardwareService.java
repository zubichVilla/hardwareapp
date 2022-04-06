package hr.tvz.zubcic.hardwareapp.hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO>save(HardwareCommand hardwareCommand);

    void deleteByCode(String code);

}

package hr.tvz.zubcic.hardwareapp.hardware;

import java.util.List;

public interface HardwareService {

    List<HardwareDTO> findAll();

    HardwareDTO findByCode(String code);

}

package hr.tvz.zubcic.hardwareapp.hardware;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepositoryImpl hardwareRepository;

    public HardwareServiceImpl(HardwareRepositoryImpl hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }


    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository
                .findAll()
                .stream()
                .map(this::mapHardwareToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HardwareDTO findByCode(String code) {
        return hardwareRepository
                .findByCode(code)
                .map(this::mapHardwareToDTO)
                .orElse(null);
    }

    @Override
    public List<HardwareDTO> findByType(String type) {
        return hardwareRepository
                .findByType(type)
                .stream()
                .map(this::mapHardwareToDTO)
                .collect(Collectors.toList());
    }

    private HardwareDTO mapHardwareToDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(),hardware.getPrice());
    }


}

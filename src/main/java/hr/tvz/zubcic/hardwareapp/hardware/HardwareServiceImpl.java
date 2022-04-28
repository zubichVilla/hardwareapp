package hr.tvz.zubcic.hardwareapp.hardware;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
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
    public List<HardwareDTO> findByRange(BigDecimal lowerRange, BigDecimal upperRange) {
        return hardwareRepository
                .findByRange(lowerRange, upperRange)
                .stream()
                .map(this::mapHardwareToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HardwareDTO> findByKeyword(String keyword) {
        return hardwareRepository
                .findByKeyword(keyword)
                .stream()
                .map(this::mapHardwareToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        return hardwareRepository
                .findByCode(code)
                .map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO>save(HardwareCommand hardwareCommand) {
        return hardwareRepository
                .save(mapCommandToHardware(hardwareCommand))
                .map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareCommand updatedHardwareCommand) {
        return hardwareRepository
                .update(code,mapCommandToHardware(updatedHardwareCommand))
                .map(this::mapHardwareToDTO);
    }

    public void deleteByCode(String code) {
        hardwareRepository.deleteByCode(code);
    }

    private HardwareDTO mapHardwareToDTO(Hardware hardware) {
            return new HardwareDTO(hardware.getCode(),
                    hardware.getName(),
                    hardware.getPrice(),
                    hardware.getType(),
                    hardware.getQuantityAvailable());
        }

    private Hardware mapCommandToHardware(HardwareCommand hardwareCommand){
        return new Hardware(hardwareCommand.getName(),
                            hardwareCommand.getCode(),
                            hardwareCommand.getPrice(),
                            hardwareCommand.getType(),
                            hardwareCommand.getQuantityAvailable());
    }
}





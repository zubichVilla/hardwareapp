package hr.tvz.zubcic.hardwareapp.hardware;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class HardwareRepositoryImpl implements HardwareRepository {

    private final List<Hardware> HARDWARE_LIST = new ArrayList<>(Arrays.asList(
            new Hardware("ASUS GTX1650", "097.100.990", BigDecimal.valueOf(2400.50),
                    Hardware.Type.GPU, 20),
            new Hardware("DDR3 4GB (1x4) Corsair", "096.100.221", BigDecimal.valueOf(221.10),
                    Hardware.Type.RAM, 5),
            new Hardware("Intel Core i5-10600K 4.1GHz", "091.900.007", BigDecimal.valueOf(2181.10),
                    Hardware.Type.CPU, 10)
    ));

    @Override
    public List<Hardware> findAll() {
        return HARDWARE_LIST;
    }



    @Override
    public Optional<Hardware> save(Hardware hardware) {
        if(isPresent(hardware)){
            return Optional.empty();
        }else {
            Hardware newHardware = new Hardware(
                    hardware.getName(),
                    hardware.getCode(),
                    hardware.getPrice(),
                    hardware.getType(),
                    hardware.getQuantityAvailable());

            HARDWARE_LIST.add(newHardware);

            System.out.println(HARDWARE_LIST);

            return Optional.of(newHardware);

        }
    }

    @Override
    public Optional<Hardware> update(String code,Hardware updatedHardware) {

        if(findByCode(code).isPresent() && code.equals(updatedHardware.getCode())){
            deleteOldAddUpdatedHardware(updatedHardware);

            return Optional.of(updatedHardware);

        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return HARDWARE_LIST.stream().filter(hardware -> Objects.equals(hardware.getCode(), code))
                .findAny();
    }

    @Override
    public List<Hardware> findByRange(BigDecimal lowerRange, BigDecimal upperRange) {
        return HARDWARE_LIST.stream()
                .filter(hardware -> (
                                     (hardware.getPrice().compareTo(lowerRange) >= 0) &&
                                     (hardware.getPrice().compareTo(upperRange) <= 0))
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByCode(String code) {
        HARDWARE_LIST.removeIf(hardware -> hardware.getCode().equalsIgnoreCase(code));
    }

    private void deleteOldAddUpdatedHardware(Hardware updatedHardware) {
        deleteByCode(updatedHardware.getCode());
        HARDWARE_LIST.add(updatedHardware);
    }

    private boolean isPresent(Hardware hardware) {
      return HARDWARE_LIST.stream()
                          .anyMatch(hardware1 -> Objects.equals(hardware1.getCode(), hardware.getCode()));

    }

}

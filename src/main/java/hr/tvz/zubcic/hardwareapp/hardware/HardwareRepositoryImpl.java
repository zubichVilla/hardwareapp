package hr.tvz.zubcic.hardwareapp.hardware;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HardwareRepositoryImpl implements HardwareRepository {

    private final List<Hardware> HARDWARE_LIST = Arrays.asList(
            new Hardware("ASUS GTX1650","097.100.990", BigDecimal.valueOf(2400.50),
                    Hardware.Type.GPU, 20),
            new Hardware("DDR3 4GB (1x4) Corsair 1333MHz","096.100.22",BigDecimal.valueOf(221.10),
                    Hardware.Type.RAM, 5),
            new Hardware("Intel Core i5-10600K 4.1GHz","091.900.007",BigDecimal.valueOf(2181.10),
                    Hardware.Type.CPU, 10)

    );

    @Override
    public List<Hardware> findAll() {
        return HARDWARE_LIST;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return HARDWARE_LIST.stream().filter(hardware -> Objects.equals(hardware.getCode(), code))
                .findAny();
    }

}

package hr.tvz.zubcic.hardwareapp.hardware;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class HardwareRepositoryImplTest {

    @Autowired
    HardwareRepository hardwareRepository;

    @Test
    void findAll() {
        List<Hardware> hardwareList = hardwareRepository.findAll();

        Assertions.assertNotNull(hardwareList);
        Assertions.assertEquals(hardwareList.size(), 4);
    }

    @DirtiesContext
    @Test
    void save() {
        Hardware hardware = new Hardware(
                "Test",
                "5555",
                BigDecimal.valueOf(10),
                Hardware.Type.GPU,
                15);

        hardwareRepository.save(hardware);

        Assertions.assertTrue(hardware.getId() == 5);

    }

    @Test
    void findByCode() {

        String TEST_CODE = "1111";

        Optional<Hardware> hardware = hardwareRepository.findByCode(TEST_CODE);

        assertTrue(hardware.isPresent());
        assertEquals(hardware.get().getCode(), TEST_CODE);

    }
}
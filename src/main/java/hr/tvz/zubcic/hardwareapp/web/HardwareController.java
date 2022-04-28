package hr.tvz.zubcic.hardwareapp.web;

import hr.tvz.zubcic.hardwareapp.hardware.HardwareCommand;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareDTO;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareService;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @GetMapping("byRange")
    public List<HardwareDTO> getHardwareByPriceRange(
            @RequestParam BigDecimal lowerRange,
            @RequestParam BigDecimal upperRange){

        return hardwareService.findByRange(lowerRange, upperRange);

    }

    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable final String code){

        return hardwareService.findByCode(code)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.OK)
                                                     .body(hardwareDTO)
                )
                .orElseGet(
                        ()-> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                           .build()
                );

    }

    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand hardwareCommand){
        return hardwareService.save(hardwareCommand)
                .map(
                        hardwareDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(hardwareDTO)
                )
                .orElseGet(
                        ()-> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );

    }

    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code,
                                              @Valid @RequestBody final HardwareCommand upadateHardwareCommand){
        return hardwareService.update(code, upadateHardwareCommand)
                              .map(ResponseEntity::ok)
                              .orElseGet(
                                      () -> ResponseEntity.notFound().build()
                              );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }

}

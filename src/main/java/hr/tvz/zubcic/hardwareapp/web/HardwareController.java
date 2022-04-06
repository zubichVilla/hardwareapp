package hr.tvz.zubcic.hardwareapp.web;

import hr.tvz.zubcic.hardwareapp.hardware.HardwareCommand;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareDTO;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
public class HardwareController {

    private final HardwareServiceImpl hardwareService;


    public HardwareController(HardwareServiceImpl hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable String code){

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }

}

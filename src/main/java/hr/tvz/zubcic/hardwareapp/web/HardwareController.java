package hr.tvz.zubcic.hardwareapp.web;

import hr.tvz.zubcic.hardwareapp.hardware.HardwareCommand;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareDTO;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareService;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Secured("ROLE_ADMIN")
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

    @Secured("ROLE_ADMIN")
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code,
                                              @Valid @RequestBody final HardwareCommand upadateHardwareCommand){
        return hardwareService.update(code, upadateHardwareCommand)
                              .map(ResponseEntity::ok)
                              .orElseGet(
                                      //() -> ResponseEntity.notFound().build()
                                      () -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                              );
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }

}

package hr.tvz.zubcic.hardwareapp.web;

import hr.tvz.zubcic.hardwareapp.hardware.HardwareDTO;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(params = "code")
    public HardwareDTO getHardwareByCode(@RequestParam final String code){
        return hardwareService.findByCode(code);
    }

    @GetMapping(params = "type")
    public List<HardwareDTO> getHardwareByType(@RequestParam final String type){
        return hardwareService.findByType(type);
    }


}

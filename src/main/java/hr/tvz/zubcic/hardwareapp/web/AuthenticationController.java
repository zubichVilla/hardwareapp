package hr.tvz.zubcic.hardwareapp.web;

import hr.tvz.zubcic.hardwareapp.security.service.AuthenticationService;
import hr.tvz.zubcic.hardwareapp.security.command.LoginCommand;
import hr.tvz.zubcic.hardwareapp.security.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("authentication")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginDTO login(@Valid @RequestBody final LoginCommand command) {
        return authenticationService.login(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials"));
    }

}

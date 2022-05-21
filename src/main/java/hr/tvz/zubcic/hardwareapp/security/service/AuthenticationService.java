package hr.tvz.zubcic.hardwareapp.security.service;

import hr.tvz.zubcic.hardwareapp.security.command.LoginCommand;
import hr.tvz.zubcic.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}

package hr.tvz.zubcic.hardwareapp.authentication;

import hr.tvz.zubcic.hardwareapp.login.LoginCommand;
import hr.tvz.zubcic.hardwareapp.login.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}

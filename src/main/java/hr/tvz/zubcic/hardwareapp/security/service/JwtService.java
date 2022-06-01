package hr.tvz.zubcic.hardwareapp.security.service;

import hr.tvz.zubcic.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}

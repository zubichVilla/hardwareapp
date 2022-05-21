package hr.tvz.zubcic.hardwareapp.jwt;

import hr.tvz.zubcic.hardwareapp.user.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}

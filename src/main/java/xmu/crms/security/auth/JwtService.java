package xmu.crms.security.auth;

import xmu.crms.entity.User;

public interface JwtService {
    /**
     * 从登录成功的用户信息生成 JWT
     *
     * @param user
     * @return
     */
    String generateJwt(User user);

    JwtPayload verifyJwt(String jwtString);
}

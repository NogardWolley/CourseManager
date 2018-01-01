package xmu.crms.security.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import xmu.crms.entity.User;

/**
 * @author LuLongfei
 * @date 2017-12-22
 */
public interface UserDetailsService {
    /**
     * 获取用户信息
     * s以 phone 开头，使用账号密码登录； 以 wechat 开头，小程序登录
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    User loadUserByUsername(String s) throws UsernameNotFoundException;
}

package xmu.crms.security.auth.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xmu.crms.dao.LoginDAO;
import xmu.crms.entity.User;

import java.net.URL;
import java.util.Map;


/**
 * 获取用户登录信息
 * spring security 中有 UserDetailsService 的定义，但考虑到登录的方式等问题，未采用其定义
 *
 * @author LuLongfei
 * @date 2017-12-21
 */
@Service
@Qualifier("customUserDetailsService")
public class UserDetailsServiceImpl implements xmu.crms.security.auth.UserDetailsService {
    @Autowired
    private LoginDAO loginDAO;

    @Value("${wechat.mp.appid}")
    private String appid;

    @Value("${wechat.mp.secret-key}")
    private String secret;
    private static final String KEY_OPEN_ID = "openid";
    private static final String KEY_ERR_CODE = "errcode";

    private static final String LOGIN_WITH_PHONE = "phone";
    private static final String LOGIN_WITH_WECHAT = "wechat";

    /**
     * 获取用户信息
     * s以 phone 开头，使用账号密码登录； 以 wechat 开头，小程序登录
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        String[] m = s.split(" ");
        User user = null;
        if (LOGIN_WITH_PHONE.equals(m[0])) {
            return getUserByPhone(m[1]);
        } else if (LOGIN_WITH_WECHAT.equals(m[0])) {
            return getUserByWechat(m[1]);
        }
        return null;
    }

    private User getUserByPhone(String phone)
            throws UsernameNotFoundException {

        User user = loginDAO.getUserLoginByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return user;
    }

    /**
     * 获取 open id
     * @param code
     * @return
     */
    private String getOpenId(String code) {
        String openId = null;
        try {
            String urlString = String.format("https://api.weixin.qq.com/sns/jscode2session?" +
                            "appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                    appid, secret, code);
            URL url = new URL(urlString);
            // json解析
            ObjectMapper mapper = new ObjectMapper();
            // 请求接口获取结果并进行json解析
            Map<String, Object> map = mapper.readValue(url, Map.class);
            if (map.get(KEY_OPEN_ID) != null) {
                openId = map.get(KEY_OPEN_ID).toString();
            } else if (map.get(KEY_ERR_CODE) != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (openId == null) {
            throw new UsernameNotFoundException("");
        }
        return openId;
    }

    private User getUserByWechat(String code)
            throws UsernameNotFoundException {

        User user = loginDAO.getUserLoginByWechat(getOpenId(code));
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return user;
    }


}

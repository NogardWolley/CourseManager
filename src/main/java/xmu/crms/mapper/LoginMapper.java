package xmu.crms.mapper;

import org.springframework.stereotype.Component;
import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * LoginMapper
 * 仅获取登录所需要的用户信息
 *
 * @author LuLongfei
 * @date 2017-12-18
 */
@Component
public interface LoginMapper {
    /**
     * 获取使用手机号登录时所需的用户信息
     *
     * @param phone
     * @return
     */
    User getUserLoginByPhone(String phone);

    /**
     * 获取微信小程序登录时所需的用户信息
     *
     * @param wechat
     * @return
     */
    User getUserLoginByWechat(String wechat);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserbyId(BigInteger id);

    /**
     * 手机注册
     *
     * @return
     */
    void createUserWithPhone(User user);

    void createUserWithWechat(User user);

    User getUserBySchoolAndNumber(BigInteger schoolId, String number);

    void addWechatToUser(User user);

    void addPhoneToUser(User user);

    void createStudentAccountByNumber(User user);
}

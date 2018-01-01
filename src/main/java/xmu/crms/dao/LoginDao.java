package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.entity.User;
import xmu.crms.mapper.LoginMapper;

import java.math.BigInteger;
import org.apache.ibatis.annotations.Param;

/**
 * @author LuLongfei
 */
@Repository
public class LoginDAO {
    @Autowired
    private LoginMapper loginMapper;

    public User getUserLoginByPhone(String phone) {
        User user = null;
        try {
            user = loginMapper.getUserLoginByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserLoginByWechat(String wechat) {
        User user = null;
        try {
            user = loginMapper.getUserLoginByPhone(wechat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public User createUserWithPhone(User user) {
        User user1 = loginMapper.getUserBySchoolAndNumber(user.getSchool().getId(), user.getNumber());
        if (user1 == null) {
            loginMapper.createUserWithPhone(user);
        } else {
            user.setId(user1.getId());
            loginMapper.addPhoneToUser(user);
        }
        return user;
    }

    public User createUserWithWechat(User user) {
        User user1 = loginMapper.getUserBySchoolAndNumber(user.getSchool().getId(), user.getNumber());
        if (user == null) {
            loginMapper.createUserWithWechat(user);
        } else {
            loginMapper.addWechatToUser(user);
        }
        return user;
    }

    public User getUserBySchoolAndNumber(BigInteger schoolId, String number) {
        return loginMapper.getUserBySchoolAndNumber(schoolId, number);
    }

    public void deleteUserById(BigInteger id) {
        loginMapper.deleteUserbyId(id);
    }

    public User createStudentAccountByNumber(User user) {
        loginMapper.createStudentAccountByNumber(user);
        return user;
    }

    public void addPhoneToUser(User user) {
        loginMapper.addPhoneToUser(user);
    }
}

package xmu.crms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xmu.crms.dao.LoginDAO;
import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.LoginService;
import xmu.crms.service.UserService;
import xmu.crms.mapper.UserMapper;

import java.math.BigInteger;
import java.net.URL;
import java.util.Map;

/**
 * @author LuLongfei
 */
@Service
public class LoginServiceImpl implements LoginService {

//    @Autowired
//    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginDAO loginDAO;

    @Value("${wechat.mp.appid}")
    private String appid;

    @Value("${wechat.mp.secret-key}")
    private String secret;

    private static final String KEY_OPEN_ID = "openid";
    // private static final String KEY_SESSION_KEY = "session_key";

    private static final String KEY_ERR_CODE = "errcode";

    @Override
    public User signInWeChat(BigInteger userId, String code, String state, String successUrl) throws UserNotFoundException {
        User val = null;
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
                val = loginDAO.getUserLoginByWechat(map.get(KEY_OPEN_ID).toString());
            } else if (map.get(KEY_ERR_CODE) != null) {
                val = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (val == null) {
            throw new UserNotFoundException();
        }
        return val;
    }

    @Override
    public User signInPhone(User user) throws UserNotFoundException {
        User val = null;
        try {
            val = loginDAO.getUserLoginByPhone(user.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (val == null) {
            throw new UserNotFoundException();
        }
        return val;
    }

    /**
     * 通过手机号注册，若为学生用户，则number存在时才能注册
     *
     * @param user 用户信息(手机号Phone和密码Password)
     * @return
     */
    @Override
    public User signUpPhone(User user) {
//        User user1 = loginDAO.getUserLoginByPhone(user.getPhone());
//        if (user1 != null) {
//            ;
//        } else {
//            return loginDAO.createUserWithPhone(user);
//        }
//        return null;

        // update
        User user1 = loginDAO.getUserLoginByPhone(user.getPhone());
        if (user1 != null) {
            ;
        } else if (user.getType() == 1) {
            return loginDAO.createUserWithPhone(user);
        } else {
            try {
                user1 = userMapper.getUserByNumber(user.getNumber());
                if (user1 != null) {
                    user.setId(user1.getId());
                    loginDAO.addPhoneToUser(user);
                    userService.updateUserByUserId(user.getId(), user);
                } else {
                    // TODO: 学生不能创建账号
                }
            } catch (UserNotFoundException e) {}
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class, UserNotFoundException.class})
    public void deleteTeacherAccount(BigInteger userId) throws IllegalArgumentException, UserNotFoundException {
        try {
//            List<Course> list = courseService.listCourseByUserId(userId);
//            for (Course course : list) {
//                courseService.deleteCourseByCourseId(course.getId());
//            }
            loginDAO.deleteUserById(userId);
        } catch (Exception e) {

        }
    }

    @Override
    public void deleteStudentAccount(BigInteger userId) throws IllegalArgumentException, UserNotFoundException {
        loginDAO.deleteUserById(userId);
    }

    @Override
    public void createStudentAccountByNumber(String number) {

            User user = userMapper.getUserByNumber(number);
            if (user == null) {
                user = new User();
                user.setType(0);
                user.setNumber(number);
                loginDAO.createStudentAccountByNumber(user);
            }

    }
}

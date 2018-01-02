package xmu.crms.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xmu.crms.entity.School;
import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.UserMapper;
import xmu.crms.security.auth.JwtService;
import xmu.crms.service.LoginService;
import xmu.crms.service.UserService;
import xmu.crms.util.JwtTokenUtil;
import xmu.crms.vo.LoginSuccessVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器。微信小程序登录与 Web 端账号密码登录
 *
 * @author LuLongfei
 */
@RestController
public class LoginController {
//    @Autowired
//    private JwtTokenUtil jwtService;

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public LoginSuccessVO register(@RequestBody Map<String, Object> map) throws NoSuchAlgorithmException ,UserNotFoundException{
        String openid=map.get("openid").toString();
        User user = new User();
        if(openid==null) {
            String phone = map.get("phone").toString();
            String password = map.get("password").toString();
            String name = map.get("name").toString();
            BigInteger schoolId = BigInteger.valueOf(Long.valueOf(map.get("schoolId").toString()));
            Integer gender = Integer.valueOf(map.get("gender").toString());
            Integer type = Integer.valueOf(map.get("type").toString());
            String email = map.get("email") == null ? null : map.get("email").toString();
            String number = map.get("number") == null ? null : map.get("number").toString();

            user.setPhone(phone);
            user.setPassword(md5Hex(password));
            user.setName(name);
            School school = new School();
            school.setId(schoolId);
            user.setSchool(school);
            user.setGender(gender);
            user.setType(type);
            user.setEmail(email);
            user.setNumber(number);
            user = loginService.signUpPhone(user);
        }
        else{
            BigInteger schoolId = BigInteger.valueOf(Long.valueOf(map.get("schoolId").toString()));
            String name = map.get("name").toString();
            String number = map.get("number") == null ? null : map.get("number").toString();
            Integer type = Integer.valueOf(map.get("Type").toString());
            user.setType(type);
            user.setName(name);
            user.setOpenid(openid);
            School school = new School();
            school.setId(schoolId);
            user.setSchool(school);
            user.setNumber(number);
            user=loginService.signInWeChat(user);
        }

        String jwt = jwtService.generateJwt(user);
        return new LoginSuccessVO(user.getId(), user.getType() == 1 ? "teacher" : "student", user.getName(), jwt);
    }

    private String md5Hex(String input) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] res = md5.digest(input.getBytes());
        return toHex(res);
    }

    private String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
    @GetMapping("/signin")
    public LoginSuccessVO login( @RequestParam("code") String code) {
        BufferedReader in=null;
        String openid=null;
        try {
            StringBuilder result =new StringBuilder();
            String appid = "wx05f27a5e70a15d8f";
            String secret = "893904dd25223c0534e1242bf3c8a929";
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret
                    + "&js_code=" + code + "&grant_type=authorization_code";
            URL realurl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            JSONObject json = new JSONObject(result.toString());
            openid = json.getString("openid");
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            System.out.println("in the shit");
            if (in!= null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();}
            }
        }
        if(openid!=null){
            User user=null;
            System.out.println(openid);
            try {
                user = userMapper.getUserByOpenIdReturnUser(openid);
            }catch (Exception e){e.printStackTrace();System.out.println("无此用户");}
            finally {

                if (user == null) {
                    System.out.println(openid);
                    return new LoginSuccessVO(openid);
                } else {
                    String jwt = jwtService.generateJwt(user);
                    return new LoginSuccessVO(user.getId(), user.getType() == 1 ? "teacher" : "student", user.getName(), jwt,openid);
                }
            }
        }
        else {return null;}

    }
}

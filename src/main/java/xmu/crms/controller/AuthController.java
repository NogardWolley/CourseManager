//package xmu.crms.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import xmu.crms.entity.JwtAuthenticationResponse;
//import xmu.crms.entity.User;
//import xmu.crms.mapper.UserMapper;
//import xmu.crms.security.UserDetailsImpl;
//import xmu.crms.service.security.AuthService;
//import xmu.crms.util.MD5Utils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.Map;
//
///**
// * @author YellowPure
// * @date
// */
//@Controller
//public class AuthController {
//
//    @Value("${jwt.header}")
//    private String tokenHeader;
//
//    @Autowired(required = false)
//    private UserMapper userMapper;
//
//    @Autowired
//    private AuthService authService;
//
//    /**
//     *登陆验证创建Jwt
//     *
//     * @author YellowPure
//     * @date 10:44 2017/12/27
//     */
//    @RequestMapping(value = "/auth")
//    public ResponseEntity createAuthenticationToken(HttpServletRequest httpServletRequest,
//                                                       HttpServletResponse httpServletResponse) throws AuthenticationException,IOException {
//
//        //将输入流转换成String
//        BufferedReader br = httpServletRequest.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        if(wholeStr == null){
//            return ResponseEntity.status(500).build();
//        }
//
//        //将字符串转换成Map
//        Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);
//        System.out.println("post登陆信息"+o.toString());
//        final String password = (String)o.get("password");
//
//        //根据用户名密码获得token
//        final String token = authService.login((String)o.get("phone"), MD5Utils.MD5encode(password));
//        UserDetailsImpl user = userMapper.getUserByPhone((String)o.get("phone"));
//
//        //根据用户type赋予角色
//        String type;
//        if(user.getType() == 0){
//            type = "student";
//        }else if(user.getType() == 1){
//            type = "teacher";
//        }else{
//            type = "unbind";
//        }
//        //返回jwt
//        return ResponseEntity.ok(new JwtAuthenticationResponse(user.getId(),type,user.getName(),token));
//    }
//
////    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
////    public ResponseEntity<?> refreshAndGetAuthenticationToken(
////            HttpServletRequest request) throws AuthenticationException{
////        String token = request.getHeader(tokenHeader);
////        String refreshedToken = authService.refresh(token);
////        if(refreshedToken == null) {
////            return ResponseEntity.badRequest().body(null);
////        } else {
////            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
////        }
////    }
//
//    /**
//     *注册
//     *
//     * @author YellowPure
//     * @date 14:03 2017/12/27
//     */
//    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
//    public ResponseEntity register(HttpServletRequest request) throws AuthenticationException, IOException{
//
//        //获取request转换成Map
//        BufferedReader br = request.getReader();
//        String str, wholeStr = "";
//        while((str = br.readLine()) != null){
//            wholeStr += str;
//        }
//        if(wholeStr == null){
//            return ResponseEntity.status(500).build();
//        }
//        Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);
//        User user = new User(o);
//        System.out.println(user.toString());
//
//        //注册用户
//        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(authService.register(user));
//    }
//
////    @RequestMapping(value = "/auth/weChat", method = RequestMethod.POST)
////    public ResponseEntity weChatLogin(HttpServletRequest request) throws IOException{
////        BufferedReader br = request.getReader();
////        String str, code = "";
////        while((str = br.readLine()) != null){
////            code += str;
////        }
////        Map<String, Object> o = new ObjectMapper().readValue(code, Map.class);
////        try{
////            Map<String, Object> ne = authService.weChatLogin((String)o.get("code"), (Integer)o.get("type"));
////            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(ne);
////        }catch (UserDuplicatedException e){
////            return ResponseEntity.status(403).build();
////        }
////    }
//
//}
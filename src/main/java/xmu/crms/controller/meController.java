package xmu.crms.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.UserService;
import xmu.crms.vo.SchoolVO;
import xmu.crms.vo.UserDetailVO;
import xmu.crms.vo.UserUpdateVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class meController {
@Resource
private UserService userService;

    /**
     *获取用户信息
     *
     * @author hj
     * @date 12月31
     */
    @RequestMapping(value = "/me", method = GET)
    @ResponseBody
    public ResponseEntity getCurrentUser() {
        BigInteger id = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            User user = userService.getUserByUserId(id);
            System.out.println(user.toString());
            if(user != null){
                UserDetailVO userDetailVO = new UserDetailVO(user);
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userDetailVO);
            }else{
                return ResponseEntity.status(404).build();
            }
        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

    /**
     *修改当前用户
     *
     * @author hj
     * @date 12月31日
     */
    @RequestMapping(value = "/me", method = PUT)
    public ResponseEntity updateCurrentUser(HttpServletRequest request) {
        BigInteger id = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (null == userService.getUserByUserId(id)) {
                return ResponseEntity.status(404).build();
            }else{
                BufferedReader br = request.getReader();
                String str, wholeStr = "";
                while((str = br.readLine()) != null){
                    wholeStr += str;
                }
                if(wholeStr == null){
                    return ResponseEntity.status(500).build();
                }
                Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);

                User user = new User(o);
                System.out.println(user.toString());
                userService.updateUserByUserId(id, user);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(204).build();
    }


//    微信小程序/OAuth2登录



}

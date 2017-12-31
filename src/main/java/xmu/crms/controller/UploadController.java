package xmu.crms.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Author:YellowDragon
 * @Description:
 * @Date:Created in 22:27 2017/12/31 0031
 * @Modified By:
 */
@Controller
@PreAuthorize("hasRole('STUDENT')")
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping(value = "/avatar", method = POST)
    @ResponseBody
    public ResponseEntity<String> uploadAvatar() {
        String url = "{\n" +
                "  \"url\": \"/avatar/3486.png\"\n" +
                "}";
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(url);
    }
}

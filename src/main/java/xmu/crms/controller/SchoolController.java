package xmu.crms.controller;

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
import xmu.crms.entity.School;
import xmu.crms.service.SchoolService;
import xmu.crms.vo.AddSchoolVO;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author HUANG KUNYI
 */


@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;
    /**
     * 得到城市所有的学校
     * @author zhouwei
     * @date 10:32 2017/12/27 0027
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity getSchoolList(@RequestParam("city") String city) {
        List<School> schools = schoolService.listSchoolByCity(city);

        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(schools);
    }
    /**
     * 创建学校
     * @author HUANG KUNYI
     */
    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity addSchool(HttpServletRequest httpServletRequest) throws IOException {
        BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        AddSchoolVO addSchoolVO = new AddSchoolVO(wholeStr);
        School school = new School(addSchoolVO);
        Map<String, Integer> result = new HashMap<String, Integer>();
        int id = schoolService.insertSchool(school).intValue();
        result.put("id", id);
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
    }
    /**
     * 获取所有的省份
     * @author HUANG KUNYI
     */
    @RequestMapping(value = "/province", method = GET)
    @ResponseBody
    public ResponseEntity getProvince() {
        List<String> provinces = new ArrayList<String>();
        provinces = schoolService.listProvince();

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(provinces);
    }
    /**
     * 获取省份所有的城市
     * @author HUANG KUNYI
     */
    @RequestMapping(value = "/city", method = GET)
    @ResponseBody
    public  ResponseEntity getCity(@RequestParam ("province") String province) {
        List<String> citys = new ArrayList<String>();
        citys = schoolService.listCity(province);

        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(citys);
    }
}
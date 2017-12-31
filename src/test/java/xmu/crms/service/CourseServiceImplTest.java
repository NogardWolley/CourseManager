package xmu.crms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.User;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.service.impl.ClassServiceImpl;
import xmu.crms.service.impl.UserServiceImpl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangyuping
 * @Description:
 * @Data: 2017/12/24 5:04
 */

// test done ! 20171225 17.10
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {
    @Autowired
    CourseDao courseDao;
    @Autowired
    ClassServiceImpl classServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    CourseService courseService;
    @Test
    public void listCourseByUserId() throws Exception {
        BigInteger userId = new BigInteger("1");
        List<Course> courseList = courseDao.listCourseByUserId(userId);
    }


}
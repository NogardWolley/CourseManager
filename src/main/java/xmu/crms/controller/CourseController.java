package xmu.crms.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;
import xmu.crms.dao.ClassDao;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.*;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.security.FifcosAuthenticationToken;
import xmu.crms.mapper.GradeMapper;
import xmu.crms.service.*;
import xmu.crms.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author HUANG KUNYI
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired(required = false)
    TimerService timerService;
    @Autowired
    TopicService topicService;
    @Autowired(required = false)
    GradeService gradeService;
    @Autowired
    CourseService courseService;
    @Autowired
    ClassService classService;
    @Autowired
    SeminarService seminarService;
    @Autowired
    UserService userService;
    @Autowired
    FixGroupService fixGroupService;

    /**
     * 获取老师的所有课程信息
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity getTeacherCourses(){
        BigInteger teacherId = (BigInteger)SecurityContextHolder.getContext().getAuthentication().getDetails();
        List<UserCourseVO> teacherCourseVOList = new ArrayList<UserCourseVO>();
        try {
            List<Course> listCourse = courseService.listCourseByUserId(teacherId);
            System.out.println(listCourse.toString());
            for(Course aListCourse : listCourse){
                List<ClassInfo> classInfoList = classService.listClassByCourseId(aListCourse.getId());
                List<User> teacherList = new ArrayList<User>();
                for (ClassInfo aClassInfoList : classInfoList){
                    teacherList.addAll(userService.listUserByClassId(aClassInfoList.getId(),"",""));
                }
                UserCourseVO teacherCourseVO =new UserCourseVO(aListCourse,classInfoList.size(),teacherList.size());
                teacherCourseVOList.add(teacherCourseVO);
            }
            return  ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(teacherCourseVOList);
        }catch (CourseNotFoundException e){
            e.printStackTrace();
            //请求中有错误，返回400
            return ResponseEntity.status(400).build();
        }catch (ClassesNotFoundException e){
            e.printStackTrace();
            //权限不足，返回403
            return ResponseEntity.status(403).build();
        }
    }

    /**
     * 获取学生列表
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/student",method = GET)
    @ResponseBody
    public ResponseEntity getCourseStudent(){
        BigInteger teacherId = (BigInteger)SecurityContextHolder.getContext().getAuthentication().getDetails();
        List<UserCourseVO> teacherCourseVOList = new ArrayList<UserCourseVO>();
        try {
            //获取老师的所有班级
            List<ClassInfo> classInfoList = classService.listClassByUserId(teacherId);
            //获取班级里所有的学生
            List<StudentClassVO> studentClassVOS = new ArrayList<StudentClassVO>();
            for(ClassInfo classInfo:classInfoList){
                System.out.println(classInfo.toString());
                studentClassVOS.add(new StudentClassVO(classInfo));
            }
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentClassVOS);
        }catch (ClassesNotFoundException e){
            e.printStackTrace();
            //无法找到资源，返回404
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * 创建课程
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(method = POST)
    @ResponseBody
    public ResponseEntity createCrouse(HttpServletRequest httpServletRequest) throws IOException{
        try {
            BufferedReader br = httpServletRequest.getReader();
            BigInteger teacherId = (BigInteger)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String str, wholeStr = "";
            //获取输入的课程信息
            while((str=br.readLine())!= null){
                wholeStr += str;
            }
            CourseVO courseVO = new CourseVO(wholeStr);
            User teacher = userService.getUserByUserId(teacherId);
            Course course = new Course(courseVO,teacher);
            int id = courseService.insertCourseByUserId(teacherId,course).intValue();
            Map<String,Integer>result = new HashMap<>();
            result.put("id",id);
            return  ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return  ResponseEntity.status(404).build();
        }
    }


    /**
     * 根据id获取学生课程信息
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/student/{classId}",method = GET)
    @ResponseBody
    public ResponseEntity getStudentCourseById(@PathVariable BigInteger classId){
        BigInteger userId = (BigInteger)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            //获取班级、课程信息
            ClassInfo classInfo = classService.getClassByClassId(classId);
            //获取分组
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId,classId);
            //获取该课程所有讨论课
            List<Seminar> seminars = seminarService.listSeminarByCourseId(classInfo.getCourse().getId());
            List<StudentSeminarBriefVO> studentSeminarBriefVOS= new ArrayList<StudentSeminarBriefVO>();
            for(Seminar seminar:seminars){
                studentSeminarBriefVOS.add(new StudentSeminarBriefVO(seminar.getId(),seminar.getName(),seminar.getFixed()==true?"fixed":"random"));
            }
            StudentCourseVO studentCourseVO =new StudentCourseVO(classInfo.getCourse().getId(),
                    classInfo.getCourse().getName(),
                    classInfo.getName(),
                    classInfo.getCourse().getDescription(),
                    fixGroup.getId(),
                    studentSeminarBriefVOS);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentCourseVO);
        }catch (CourseNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }catch (IllegalArgumentException e){
            e.printStackTrace();;
            return ResponseEntity.status(400).build();
        }catch (ClassesNotFoundException e){
            e.printStackTrace();;
            return ResponseEntity.status(400).build();
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }

    /**
     * 根据课程id获取课程
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')" )
    @RequestMapping(value = "/{courseId}",method = GET)
    @ResponseBody
    public ResponseEntity getCourseById(@PathVariable int courseId){
        try{
            Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
            GetCourseVO getCourseVO = new GetCourseVO(course);
            return  ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(getCourseVO);
        }catch (CourseNotFoundException e){
            e.printStackTrace();
            return  ResponseEntity.status(404).build();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return  ResponseEntity.status(400).build();
        }
    }

    /**
     * 修改课程信息
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{courseId}",method = PUT)
    @ResponseBody
    public ResponseEntity updateCourseById(@PathVariable int courseId,HttpServletRequest httpServletRequest)throws IOException{
        BufferedReader br = httpServletRequest.getReader();
        BigInteger userId = (BigInteger)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String str,wholeStr = "";
        while ((str = br.readLine()) != null){
            wholeStr += str;
        }
        CourseVO courseVO = new CourseVO(wholeStr);
        Course course = new Course(courseVO);
        courseService.updateCourseByCourseId(BigInteger.valueOf(courseId),course);
        return  ResponseEntity.status(204).build();
    }

    /**
     * 删除课程
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{courseId}",method = DELETE)
    @ResponseBody
    public ResponseEntity deleteCourseById(@PathVariable int courseId){
        try{
            courseService.deleteCourseByCourseId(BigInteger.valueOf(courseId));
            return ResponseEntity.status(204).build();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }

    /**
     * 按id获取课程班级列表
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{courseId}/class", method = GET)
    @ResponseBody
    public ResponseEntity getClassListByCourseId(@PathVariable int courseId){
        try{
            List<ClassVO> list = new ArrayList<ClassVO>();
            List<ClassInfo> listClass = new ArrayList<ClassInfo>();
            listClass = classService.listClassByCourseId(BigInteger.valueOf(courseId));
            for(ClassInfo classList : listClass){
                ClassVO classVO = new ClassVO(classList.getId(),classList.getName());
                list.add(classVO);
            }
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(list);
        }catch (CourseNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }


    /**
     * 在指定了id的课程中创建班级
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{courseId}/class",method = POST)
    @ResponseBody
    public ResponseEntity createClasssByCrouseId(@PathVariable int courseId,HttpServletRequest httpServletRequest)throws UserNotFoundException,IOException{
        try{
            BufferedReader br = httpServletRequest.getReader();
            BigInteger teacherId=(BigInteger)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String str,wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            ClassCreateVO classCreateVO = new ClassCreateVO(wholeStr);
            Map<String, Integer> result = new HashMap<String, Integer>();
            Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
            ClassInfo classInfo = new ClassInfo(classCreateVO);
            int id = classService.insertClassById(BigInteger.valueOf(courseId), classInfo).intValue();
            result.put("id", id);
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
        }catch (CourseNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * 按课程ID获取讨论课详情列表
     * @author HUANG KUNYI
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{courseId}/teacher/seminar", method = GET)
    @ResponseBody
    public ResponseEntity getSeminarsByCourseId(@PathVariable int courseId) throws IllegalArgumentException{
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            List<Seminar> seminarList = seminarService.listSeminarByCourseId(BigInteger.valueOf(courseId));
            System.out.println(seminarList);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarList);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }


/**
 * 按id获取正在进行的讨论课详情
 * @author HUANG KUNYI
 */
@PreAuthorize("hasRole('STUDENT')")
@RequestMapping(value = "/{courseId}/student/seminar" , method = GET)
@ResponseBody
public ResponseEntity getStudentSeminarsByCourseId(@PathVariable int courseId) throws IllegalArgumentException{
    BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<SeminarGroup> listSeminarGroup = new ArrayList<SeminarGroup>();
    List<SeminarAndGradeVO> listSeminarAndGradeVO = new ArrayList<SeminarAndGradeVO>();

    listSeminarGroup = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));
    System.out.println(listSeminarGroup);
    for (SeminarGroup seminarGroup : listSeminarGroup) {
        SeminarAndGradeVO seminarAndGradeVO = new SeminarAndGradeVO(seminarGroup);
        listSeminarAndGradeVO.add(seminarAndGradeVO);
    }
    return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listSeminarAndGradeVO);
}


/**
 * 在指定ID的课程创建讨论课
 * @author HUANG KUNYI
 */
@PreAuthorize("hasRole('TEACHER')")
@RequestMapping(value = "/{courseId}/seminar", method = POST)
@ResponseBody
public ResponseEntity createSeminarByCourseId(@PathVariable int courseId, HttpServletRequest httpServletRequest) throws IOException {
    try {
        //创建seminar
        BufferedReader br = httpServletRequest.getReader();
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        SeminarUpdateVO seminarUpdateVO = new SeminarUpdateVO(wholeStr);
        Map<String, BigInteger> result = new HashMap<String, BigInteger>();
        Seminar seminar = new Seminar(seminarUpdateVO);

        BigInteger id = seminarService.insertSeminarByCourseId(BigInteger.valueOf(courseId), seminar);
        result.put("id", id);
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
    } catch (CourseNotFoundException e) {
        e.printStackTrace();
        return ResponseEntity.status(404).build();
    }
}

/**
 *按课程ID获取学生的所有讨论课成绩
 * @author HUANG KUNYI
 */
@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
@RequestMapping(value = "/{courseId}/grade", method = GET)
@ResponseBody
public ResponseEntity getAllGradeByCourseId(@PathVariable int courseId){
    try {
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SeminarGroup> seminarGroups = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));
        List<SeminarGradeVO> seminarGradeVOS = new ArrayList<SeminarGradeVO>();
        for (SeminarGroup seminarGroup : seminarGroups) {
            List<SeminarGroupTopic> seminarGroupTopics = topicService.listSeminarGroupTopicByGroupId(seminarGroup.getId());
            for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopics){
                Topic topic = topicService.getTopicByTopicId(seminarGroupTopic.getTopic().getId());
                SeminarGradeVO seminarGradeVO = new SeminarGradeVO(seminarGroup, topic);
                seminarGradeVOS.add(seminarGradeVO);
            }
        }
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGradeVOS);
    } catch (TopicNotFoundException e) {
        e.printStackTrace();
        return ResponseEntity.status(404).build();
    }
}

}


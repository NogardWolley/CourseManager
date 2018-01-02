package xmu.crms.controller;

import io.jsonwebtoken.lang.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupTopic;
import xmu.crms.entity.User;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.*;
import xmu.crms.vo.*;
import xmu.crms.exception.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author hj
 * @date 10:49 2017/12/31
 */
@RequestMapping("/class")
public class classController {


    @Autowired
    ClassService classService;

    @Autowired
    UserService userService;

    @Autowired
    FixGroupService fixGroupService;

    @Autowired
    SeminarGroupService seminarGroupService;

    @Autowired
    TopicService topicService;
    /**
     * 获取可以选的课程
     *
     * @author hj
     * @date 10:49 2017/12/31
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(method = GET)
    @ResponseBody
    public ResponseEntity getAvailableClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
                                                      @RequestParam(value = "courseTeacher", required = false) String teacherName) {
        System.out.print("in 1");
        //获取当前用户id
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.print(userId);
        List<ClassInfo> allClassInfos=null;
        try {
            //获取所有班级信息
            try {
                allClassInfos = classService.listClassByName(courseName, teacherName);
            }
            catch (Exception e){}
            System.out.println(allClassInfos);
            List<ClassInfo> myClassInfos = new ArrayList<>();
            try {
                //获取当前用户所选的班级信息
                myClassInfos = classService.listClassByUserId(userId);
            } catch (Exception e) {

            } finally {
                List<UserClassVO> userClassVOS = new ArrayList<>();
                //获取所有课程里没有我所选的班级的课程
                //有选课信息
                if (myClassInfos != null) {
                    System.out.println(myClassInfos);
                    List<BigInteger> myClassCourseId = new ArrayList<>();
                    for (ClassInfo classInfo : myClassInfos) {
                        myClassCourseId.add(classInfo.getCourse().getId());
                    }

                    for (ClassInfo classInfo : allClassInfos) {
                        if (!myClassCourseId.contains(classInfo.getCourse().getId())) {
                            try {
                                List<User> users = userService.listUserByClassId(classInfo.getId(), null, null);
                                userClassVOS.add(new UserClassVO(classInfo, users.size()));
                            } catch (Exception e) {

                            }
                        }
                    }
                }
                //没有选课信息
                else {
                    for (ClassInfo classInfo : allClassInfos) {
                        try {
                            List<User> users = userService.listUserByClassId(classInfo.getId(), null, null);
                            userClassVOS.add(new UserClassVO(classInfo, users.size()));
                        } catch (Exception e) {
                        }
                    }
                }
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userClassVOS);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }


    /**
     *根据id获取班级信息（班级信息，学生信息）
     *
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}", method = GET)
    @ResponseBody
    public ResponseEntity getClassById(@PathVariable BigInteger classId) {
        ClassInfo classInfo = null;
        List<User> users = null;
        try {
            //获取班级信息
            try {
                classInfo = classService.getClassByClassId(classId);
            }
            catch (Exception e){}
            //获取该班级学生
            try {
                users = userService.listUserByClassId(classId, null, null);
            }
            catch(Exception e){}
            ClassDetailVO classDetailVO = new ClassDetailVO(classInfo, users.size());
            System.out.println(classDetailVO);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(classDetailVO);
        } catch (Exception e) {
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON_UTF8).build();
        }
    }

    /**
     *按照ID修改班级
     *
     * @author hj
     * @date 2017年12月31日
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{classId}", method = PUT)
    @ResponseBody
    public ResponseEntity updateClassById(@PathVariable BigInteger classId,
                                          HttpServletRequest httpServletRequest) throws IOException {
        //将JSON转换成对象
        BufferedReader br = httpServletRequest.getReader();
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        ClassCreateVO classCreateVO = new ClassCreateVO(wholeStr);
        ClassInfo classInfo = new ClassInfo(classCreateVO);

        try {
            classService.updateClassByClassId(classId, classInfo);
            return ResponseEntity.status(204).build();
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }

    /**
     *按ID删除班级
     *
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{classId}", method = DELETE)
    @ResponseBody
    public ResponseEntity deleteClassById(@PathVariable BigInteger classId) {
        try {
            classService.deleteClassByClassId(classId);
            return ResponseEntity.status(204).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     *按ID查找学生列表（查询学号、姓名开头）
     *
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/student", method = GET)
    @ResponseBody
    public ResponseEntity getStudentListByClassId(@PathVariable BigInteger classId,
                                                  @RequestParam(value = "numberBeginWith", required = false) String numberBeginWith,
                                                  @RequestParam(value = "nameBeginWith", required = false) String nameBeginWith) {
        List<User> list = new ArrayList<User>();
        try {
            System.out.println(numberBeginWith + ':' + nameBeginWith);
            list = userService.listUserByClassId(classId, numberBeginWith, nameBeginWith);
        } catch (ClassesNotFoundException e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        } else {
            List<ClassStudentVO> listStudent = new ArrayList<ClassStudentVO>();
            for (int i = 0; i < list.size(); i++) {
                ClassStudentVO classStudentVO = new ClassStudentVO(list.get(i).getId(), list.get(i).getName(), list.get(i).getNumber());
                listStudent.add(classStudentVO);
            }
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listStudent);
        }
    }


/**
 *学生按ID选择班级
 *
 * @author hj
 * @date 12月31日
 */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/student", method = POST)
    @ResponseBody
    public ResponseEntity selectClass(@PathVariable BigInteger classId) {
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            classService.insertCourseSelectionById(userId, classId);
            return ResponseEntity.status(204).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     *  学生按ID取消选择班级
     *
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/student/{studentId}", method = DELETE)
    @ResponseBody
    public ResponseEntity deSelectClass(@PathVariable BigInteger classId, @PathVariable BigInteger studentId) {
        try {
            classService.deleteCourseSelectionById(studentId, classId);
            return ResponseEntity.status(204).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }



    /**
     *按ID获取自身所在班级小组
     *
     * @author YellowPure
     * @date 11:07 2017/12/27
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/classgroup", method = GET)
    @ResponseBody
    public ResponseEntity getGroupByClassId(@PathVariable BigInteger classId) {

        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
            List<User> members = fixGroupService.listFixGroupMemberByGroupId(fixGroup.getId());
            System.out.println(members.toString());
            FixGroupVO fixGroupVO = new FixGroupVO(fixGroup, members);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(fixGroupVO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (FixGroupNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     *
     班级小组组长辞职
     *
     * @author hj
     * @date 12月31日
            */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/classgroup/resign", method = PUT)
    public ResponseEntity resignFromGroup(@PathVariable BigInteger classId) {
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
            seminarGroupService.resignLeaderById(fixGroup.getId(), userId);
            return ResponseEntity.status(204).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (GroupNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     *成为班级小组组长
     *
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/classgroup/assign", method = PUT)
    public ResponseEntity assignFromGroup(@PathVariable BigInteger classId) {
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
            seminarGroupService.assignLeaderById(fixGroup.getId(), userId);
            return ResponseEntity.status(204).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (GroupNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     *添加班级小组成员
     *
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/classgroup/add", method = PUT)
    public ResponseEntity addMemberInGroup(@PathVariable BigInteger classId, @RequestBody UserVO user) {
        BigInteger userId = user.getId();
        try {
            /*每个人一开始都是fixGroup的leader， 若插入的时候组内还只有一个人，
            说明目前的组是个虚拟的组，要现在数据库里面插入一个fixGroup的记录
			 */
            BigInteger groupId = BigInteger.valueOf(0);
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);

            //如果没有小组，创建固定分组，设置班级信息，组长信息
            if (fixGroup == null) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setId(classId);
                User leader = new User();
                leader.setId(userId);
                FixGroup fixGroup1 = new FixGroup();
                fixGroup1.setClassInfo(classInfo);
                fixGroup1.setLeader(leader);
                fixGroupService.insertFixGroupByClassId(userId, classId);
            }
            fixGroupService.insertStudentIntoGroup(userId, groupId);
            return ResponseEntity.status(204).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (InvalidOperationException e) {
            return ResponseEntity.status(403).build();
        } catch (FixGroupNotFoundException e) {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     *删除组内成员
     *
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/classgroup/remove", method = PUT)
    public ResponseEntity deleteMemberFromGroup(@PathVariable BigInteger classId,
                                                @RequestBody UserVO userVO) {
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            //获取小组
            FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
            //删除小组成员
            fixGroupService.deleteFixGroupUserById(fixGroup.getId(), userVO.getId());
            return ResponseEntity.status(204).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (FixGroupNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ClassesNotFoundException e) {
            return ResponseEntity.status(404).build();
        }


    }

    /**
     *班级小组按删除话题
     * @author hj
     * @date 12月31日
     */
    @PreAuthorize(" hasRole('STUDENT')")
    @RequestMapping(value = "/{classId}/classgroup/topic/{topicId}", method = DELETE)
    public  ResponseEntity chooseGroopTopic (@PathVariable Integer groupId,
                                             @RequestBody TopicVO topicId){
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
           topicService.deleteSeminarGroupTopicById(BigInteger.valueOf(groupId), topicId.getId());
            return ResponseEntity.status(204).build();
        }
            catch(IllegalArgumentException e ){
                return ResponseEntity.status(400).build();
            }
    }


//    /**
//     *班级小组Id选择话题
//     * @author hj
//     * @date 12月31日
//     */
//    @PreAuthorize(" hasRole('STUDENT')")
//    @RequestMapping(value = "/{classId}/classgroup/topic", method = POST)
//    public  ResponseEntity chooseGroopTopic (@PathVariable Integer groupId,
//                                             @RequestBody TopicVO topicId){
//        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        try {
//            FixGroupTopic fixGroupTopic = topicService.deleteSeminarGroupTopicById(BigInteger.valueOf(groupId), topicId.getId())
//            return ResponseEntity.status(201).build();
//        }
//        catch(IllegalArgumentException e ){
//            return ResponseEntity.status(403).build();
//        }
//    }

}

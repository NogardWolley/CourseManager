//package xmu.crms.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import xmu.crms.entity.ClassInfo;
//import xmu.crms.entity.User;
//import xmu.crms.exception.CourseNotFoundException;
//import xmu.crms.exception.UserNotFoundException;
//import xmu.crms.service.ClassService;
//import xmu.crms.service.FixGroupService;
//import xmu.crms.service.SeminarGroupService;
//import xmu.crms.service.UserService;
//import xmu.crms.vo.ClassVO;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
///**
// * @author hj
// * @date 10:49 2017/12/31
// */
//public class classController {
//
//
//    @Autowired
//    ClassService classService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    FixGroupService fixGroupService;
//
//    @Autowired
//    SeminarGroupService seminarGroupService;
//    /**
//     * 获取可以选的课程
//     *
//     * @author hj
//     * @date 10:49 2017/12/31
//     */
//    @PreAuthorize("hasRole('STUDENT')")
//    @RequestMapping(method = GET)
//    @ResponseBody
//    public ResponseEntity getAvailableClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
//                                                      @RequestParam(value = "courseTeacher", required = false) String teacherName) {
//        //获取当前用户id
//        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<ClassInfo> allClassInfos=null;
//        try {
//            //获取所有班级信息
//            try {
//                allClassInfos = classService.listClassByName(courseName, teacherName);
//            }
//            catch (Exception e){}
//            System.out.println(allClassInfos);
//            List<ClassInfo> myClassInfos = new ArrayList<>();
//            try {
//                //获取当前用户所选的班级信息
//                myClassInfos = classService.listClassByUserId(userId);
//            } catch (Exception e) {
//
//            } finally {
//                List<ClassVO> userClassVOS = new ArrayList<>();
//                //获取所有课程里没有我所选的班级的课程
//                //有选课信息
//                if (myClassInfos != null) {
//                    System.out.println(myClassInfos);
//                    List<BigInteger> myClassCourseId = new ArrayList<>();
//                    for (ClassInfo classInfo : myClassInfos) {
//                        myClassCourseId.add(classInfo.getCourse().getId());
//                    }
//
//                    for (ClassInfo classInfo : allClassInfos) {
//                        if (!myClassCourseId.contains(classInfo.getCourse().getId())) {
//                            try {
//                                List<User> users = userService.listUserByClassId(classInfo.getId(), null, null);
//                                userClassVOS.add(new UserClassVO(classInfo, users.size()));
//                            } catch (Exception e) {
//
//                            }
//                        }
//                    }
//                }
//                //没有选课信息
//                else {
//                    for (ClassInfo classInfo : allClassInfos) {
//                        try {
//                            List<User> users = userService.listUserByClassId(classInfo.getId(), null, null);
//                            userClassVOS.add(new UserClassVO(classInfo, users.size()));
//                        } catch (Exception e) {
//                        }
//                    }
//                }
//                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userClassVOS);
//            }
//
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).build();
//        } catch (CourseNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).build();
//        }
//    }
//
//
//
//
//}

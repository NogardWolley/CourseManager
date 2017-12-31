package xmu.crms.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.User;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.service.ClassService;
import xmu.crms.service.CourseService;
import xmu.crms.service.SeminarService;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Huang KunYI
 */
@Mapper
@Component
public interface CourseMapper {
    /**
     * 按userId获取与当前用户相关联的课程列表.
     * <p>老师与他相关联的课程列表<br>
     *
     * @param userId 用户Id
     * @return null 课程列表
     * @throws IllegalArgumentException userId格式错误时抛出
     * @throws CourseNotFoundException  未找到课程
     * @author Huang KunYI
     */
    List<Course> listCourseByUserId(BigInteger userId);


    /**
     * 按userId创建课程.
     * <p>按userId创建课程<br>
     *
//     * @param userId 用户Id
     * @param course 课程信息
     * @return courseId 新建课程的id
     * @throws IllegalArgumentException userId格式错误时抛出
     * @author Huang KunYI
     */
    Integer insertCourseByUserId( Course course);


    /**
     * 按courseId获取课程 .
     * <p>按courseId获取课程 <br>
     *
     * @param courseId 课程Id
     * @return course
     * @throws IllegalArgumentException courseId格式错误时抛出
     * @throws CourseNotFoundException  未找到课程
     * @author Huang KunYI
     */
    Course getCourseByCourseId(BigInteger courseId);

    /**
     * 按课程获取 courseId.
     * <p>按课程获取 courseId<br>
     *
     * @param CourseName 课程名
     * @return courseID
     * @throws CourseNotFoundException  未找到课程
     * @author Huang KunYI
     */
    List<Course> listCourseIdByCourseName(String CourseName);

    /**
     * 按用户名获取 userId.
     * <p>按用户名获取 userId<br>
     *
     * @param UserName 用户名
     * @return userID
     * @throws CourseNotFoundException  未找到课程
     * @author Huang KunYI
     */
    BigInteger getUserIdByUserName(String UserName);

    /**
     * 按userId获取 courseId.
     * <p>按userId取 courseId<br>
     *
     * @param TeacherId 用户id
     * @return courseID
     * @throws CourseNotFoundException  未找到课程
     * @author Huang KunYI
     */
    List<Course> listCourseIdByTeacherId(BigInteger TeacherId);


    /**
     * 传入courseId和course信息修改course信息.
     * <p>传入courseId和course信息修改course信息 <br>
     *
//     * @param courseId 课程Id
     * @param course   课程信息
     * @throws CourseNotFoundException 未找到课程
     * @author Huang KunYI
     */
    int updateCourseByCourseId( Course course);


    /**
     * 按courseId删除课程.
     * <p>先根据courseID删除Seminar 和 class,然后再将course的信息删除<br>
     *
     * @author Huang KunYI
     * @param courseId 课程Id
     * @throws IllegalArgumentException courseId格式错误时抛出
     * @see SeminarService #deleteSemiarByCourseId(BigInteger courseId)
     * @see ClassService   #deleteClassByCourseId(BigInteger courseId)
     */
    int deleteCourseByCourseId(BigInteger courseId);


    /**
     * 根据课程名称获取课程列表.
     * <p>根据课程名称获取课程列表<br>
     *
     * @author Huang KunYI
     * @param courseName 课程名称
     * @return list 课程列表
     * @see CourseService #getCourseByCourseId(BigInteger courseId)
     */
    List<Course> listCourseByCourseName(String courseName);


    /**
     * 按课程名称获取班级列表.
     * <p>根据课程名获取课程ID，通过课程ID获取班级列表<br>
     *
//     * @param courseName 课程名称
     * @return list 班级列表
     * @author Huang KunYI
     * @see xmu.crms.service.CourseService #listCourseByCourseName(String courseName)
     * @see ClassService #listClassByCourseId(BigInteger courseId)
     */
    List<ClassInfo> listClassByCourseId(BigInteger courseId);






}


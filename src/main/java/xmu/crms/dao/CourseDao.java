package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.Course;
import xmu.crms.entity.ClassInfo;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.mapper.CourseMapper;

import java.math.BigInteger;
import java.util.List;

@Component
public class CourseDao {
    @Autowired
    CourseMapper courseMapper;
    public List<Course> listCourseByUserId(BigInteger userId)
        throws IllegalArgumentException,CourseNotFoundException{
        if(!(userId.intValue()>0)){
            throw new IllegalArgumentException("用户ID格式错误！");
        }
        List<Course> courseList = courseMapper.listCourseByUserId(userId);
        if(courseList == null){
            throw new CourseNotFoundException("没有找到此课程！");
        }
        return courseList;
    }

    public BigInteger insertCourseByUserId(BigInteger userId,Course course)
        throws IllegalArgumentException{
        if(!(userId.intValue()>0)){
            throw new IllegalArgumentException("用户ID格式错误！");
        }
        course.getTeacher().setId(userId);
        Integer courseId = courseMapper.insertCourseByUserId(course);
        return BigInteger.valueOf(courseId);
    }
    public Course getCourseByCourseId(BigInteger courseId)
        throws  IllegalArgumentException,CourseNotFoundException{
        if (!(courseId.intValue()>0)){
            throw new IllegalArgumentException("课程ID格式错误！");
        }
        Course course = courseMapper.getCourseByCourseId(courseId);
        if(courseId ==null){
            throw new CourseNotFoundException("没有找到此课程！");
        }
        return course;
    }

    public int updateCourseByCourseId(BigInteger courseId,Course course) {
        course.setId(courseId);
        int matchUpdateLines = courseMapper.updateCourseByCourseId(course);
        return matchUpdateLines;
    }

    public int deleteCourseByCourseId(BigInteger courseId)
        throws IllegalArgumentException{
        if (!(courseId.intValue()>0)){
            throw new IllegalArgumentException("用户ID格式错误！");
        }
        int matchDeleteLines = courseMapper.deleteCourseByCourseId(courseId);
        return matchDeleteLines;
    }

    public List<Course>listCourseByCourseName(String courseName){
        List<Course>courseList = courseMapper.listCourseByCourseName(courseName);
        return courseList;
    }

    public List<ClassInfo> listClassByCourseId(BigInteger courseId)
            throws CourseNotFoundException{
        if(courseId == null){
            throw new CourseNotFoundException("没有找到此课程！");
        }
        List<ClassInfo>list=courseMapper.listClassByCourseId(courseId);
        return list;
    }

    public BigInteger getUserIdByUserName(String userName)
    {
        return courseMapper.getUserIdByUserName(userName);
    }

    public List<Course>listCourseIdByTeacherId(BigInteger teacherId){
        return courseMapper.listCourseIdByTeacherId(teacherId);
    }

    public List<Course>listCourseIdByCourseName(String courseName){
        return courseMapper.listCourseIdByCourseName(courseName);
    }

}

package xmu.crms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.CourseService;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Override
    public List<Course> listCourseByUserId(BigInteger userId)
            throws IllegalArgumentException, CourseNotFoundException {
        return courseDao.listCourseByUserId(userId);

    }
    @Override
    public BigInteger insertCourseByUserId(BigInteger userId, Course course)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return courseDao.insertCourseByUserId(userId, course);
    }
    @Override
    public Course getCourseByCourseId(BigInteger courseId)
            throws IllegalArgumentException, CourseNotFoundException {
        // TODO Auto-generated method stub
        Course course = courseDao.getCourseByCourseId(courseId);
        return course;
    }
    @Override
    public void updateCourseByCourseId(BigInteger courseId, Course course) {
        // TODO Auto-generated method stub
        courseDao.updateCourseByCourseId(courseId, course);
    }
    @Override
    public void deleteCourseByCourseId(BigInteger courseId)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        courseDao.deleteCourseByCourseId(courseId);
    }
    @Override
    public List<Course> listCourseByCourseName(String courseName) {
        // TODO Auto-generated method stub
        return courseDao.listCourseByCourseName(courseName);
    }


    @Override
    public List<ClassInfo> listClassByCourseName(String courseName)
            throws CourseNotFoundException {
        // TODO Auto-generated method stub
        List<Course> courseList = this.listCourseByCourseName(courseName);
        if(courseList == null)
        {
            throw new CourseNotFoundException("没有找到课程！");
        }
        List<ClassInfo> classInfoList = new LinkedList<ClassInfo>();
        for(int i = 0; i < courseList.size(); i++)
        {
            BigInteger id = courseList.get(i).getId();
            classInfoList.addAll(courseDao.listClassByCourseId(id));
        }
        return classInfoList;
    }



    @Override
    public List<ClassInfo> listClassByTeacherName(String teacherName)
            throws UserNotFoundException,ClassesNotFoundException {
        // TODO Auto-generated method stub
        try
        {
            BigInteger userId = courseDao.getUserIdByUserName(teacherName);
            List<Course> courseList = courseDao.listCourseByUserId(userId);
            List<ClassInfo> classInfoList = new LinkedList<ClassInfo>();
            for(int i = 0; i < courseList.size(); i++)
            {
                BigInteger courseId = courseList.get(i).getId();
                classInfoList.addAll(courseDao.listClassByCourseId(courseId));
            }
            return classInfoList;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public List<ClassInfo> listClassByName(String courseName, String teacherName)
            throws UserNotFoundException, CourseNotFoundException{
        if(teacherName == null){
            throw new UserNotFoundException("查无此人！");
        }
        BigInteger userID = courseDao.getUserIdByUserName(teacherName);
        List<Course> courseList=courseDao.listCourseIdByTeacherId(userID);
        List<Course> CourseList=courseDao.listCourseIdByCourseName(courseName);
        BigInteger id = null;
        for(int i = 0; i < courseList.size(); i++)
        {
            BigInteger courseId = courseList.get(i).getId();
            for(int j = 0;j < CourseList.size();j++) {
                BigInteger CourseId = CourseList.get(i).getId();
                if(CourseId == courseId){
                    id=CourseId;
                }
            }
        }
        if(id == null){
            throw new CourseNotFoundException("没有找到此课程！");
        }
        List<ClassInfo>list=courseDao.listClassByCourseId(id);
        return list ;
    }
}
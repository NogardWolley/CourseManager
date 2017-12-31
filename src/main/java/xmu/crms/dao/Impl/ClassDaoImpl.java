package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.dao.ClassDao;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.ClassMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassDaoImpl implements ClassDao {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassInfo> listClassByName(String courseName, String teacherName) throws UserNotFoundException, CourseNotFoundException,ClassesNotFoundException {
        List<ClassInfo> classes;
        User teacher = classMapper.findTeacherIdByteacherName(teacherName);
        if(teacher == null){
            throw new UserNotFoundException();
        }else{
            BigInteger teacherId = teacher.getId();
            Course course = classMapper.findCourseIdByteacherIdAndCourseName(teacherId,courseName);
            if(course == null){
                throw new CourseNotFoundException();
            }else{
                BigInteger courseId = course.getId();
                classes = classMapper.findClassByCourseId(courseId);
                if(classes == null){
                    throw new ClassesNotFoundException();
                }else{
                    return classes;
                }
            }
        }

    }

    @Override
    public Boolean deleteClassSelectionByClassId(BigInteger classId) {
        return classMapper.deleteClassSelectionByClassId(classId);
    }

    @Override
    public List<ClassInfo> findClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
        List<ClassInfo> classes;
        classes = classMapper.findClassByCourseId(courseId);
        if(classes == null) {
            throw new CourseNotFoundException();
        }
        return classes;
    }

    @Override
    public ClassInfo findClassByClassId(BigInteger classId) throws ClassesNotFoundException {
        ClassInfo classes;
        classes = classMapper.findClassByClassId(classId);
        if(classes == null) {
            throw new ClassesNotFoundException();
        }
        return classes;
    }

    @Override
    public Boolean updateClassByClassId(BigInteger classId, ClassInfo newClass) throws ClassesNotFoundException {
        ClassInfo classInfo = classMapper.findClassByClassId(classId);
        if(classInfo == null){
            throw new ClassesNotFoundException();
        }
        newClass.setId(classId);
        return classMapper.updateClassByClassId(newClass);

    }

    @Override
    public Boolean deleteClassByClassId(BigInteger classId) throws ClassesNotFoundException {
        ClassInfo classInfo = classMapper.findClassByClassId(classId);
        if(classInfo == null){
            throw new ClassesNotFoundException();
        }else{
            return classMapper.deleteClassByClassId(classId);
        }
    }

    @Override
    public BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId) throws UserNotFoundException, ClassesNotFoundException {
        User student = classMapper.findStudentdByStudentId(userId);
        if(student == null){
            throw new UserNotFoundException();
        }else {
            ClassInfo classInfo = classMapper.findClassByClassId(classId);
            if(classInfo == null){
                throw new ClassesNotFoundException();
            }else{
                CourseSelection courseSelection = new CourseSelection();
                ClassInfo classInfo1 = new ClassInfo();
                classInfo1.setId(classId);
                courseSelection.setClassInfo(classInfo1);
                User student1 = new User();
                student1.setId(userId);
                courseSelection.setStudent(student1);
                Integer b = classMapper.insertCourseSelectionById(courseSelection);
                if (b > 0)
                {
                    return courseSelection.getId();
                }else {
                    return BigInteger.valueOf(-1);
                }
            }
        }
    }

    @Override
    public Boolean deleteCourseSelectionById(BigInteger userId, BigInteger classId) throws UserNotFoundException, ClassesNotFoundException {
        User student = classMapper.findStudentdByStudentId(userId);
        if(student == null){
            throw new UserNotFoundException();
        }else {
            ClassInfo classInfo = classMapper.findClassByClassId(classId);
            if(classInfo == null){
                throw new ClassesNotFoundException();
            }else{
                return classMapper.deleteCourseSelectionById(userId,classId);
            }
        }

    }

    @Override
    public BigInteger findCourseIdByUserIdAndClassId(BigInteger userId, BigInteger classId) {
        return classMapper.findCourseIdByUserIdAndClassId(userId,classId);
    }

    @Override
    public Location getCallStatusById(BigInteger classId, BigInteger seminarId) throws SeminarNotFoundException {
        Seminar seminar = classMapper.findSeminarById(seminarId);
        if(seminar == null){
            throw new SeminarNotFoundException();
        }else{
            return classMapper.getCallStatusById(classId,seminarId);
        }
    }


    @Override
    public BigInteger insertClassById(BigInteger courseId, ClassInfo classInfo) throws CourseNotFoundException {
        Course course = classMapper.findCourseByCourseId(courseId);
        if(course == null){
            throw new CourseNotFoundException();
        }else{
            Integer b = classMapper.insertClassById(classInfo);
            if (b > 0)
            {
                return classInfo.getId();
            }else {
                return BigInteger.valueOf(-1);
            }
        }


    }

    @Override
    public void deleteClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
        Course course = classMapper.findCourseByCourseId(courseId);
        if(course == null){
            throw new CourseNotFoundException();
        }else {
            classMapper.deleteClassByCourseId(courseId);
        }
    }


    @Override
    public void deleteScoreRuleById(BigInteger classId) throws ClassesNotFoundException {
        ClassInfo classInfo = classMapper.findClassByClassId(classId);
        if(classInfo == null){
            throw new ClassesNotFoundException();
        }else{
            classMapper.deleteScoreRuleById(classId);
        }
    }

    @Override
    public ClassInfo getScoreRule(BigInteger classId) throws ClassesNotFoundException {
        ClassInfo classInfo = classMapper.findClassByClassId(classId);
        if(classInfo == null){
            throw new ClassesNotFoundException();
        }else {
            classInfo = classMapper.getScoreRule(classId);
            return classInfo;
        }
    }

    @Override
    public BigInteger insertScoreRule(BigInteger classId, ClassInfo proportions) throws InvalidOperationException, ClassesNotFoundException {
        ClassInfo classInfo = classMapper.findClassByClassId(classId);
        if(classInfo == null){
            throw new ClassesNotFoundException();
        }else{
            if(proportions.getReportPercentage()+proportions.getPresentationPercentage()!=100||proportions.getFivePointPercentage()+proportions.getFourPointPercentage()+proportions.getThreePointPercentage()!=100){
                throw new InvalidOperationException();
            }else{
                proportions.setId(classId);
                Integer b = classMapper.insertScoreRule(proportions);
                if (b > 0)
                {
                    return proportions.getId();
                }else {
                    return BigInteger.valueOf(-1);
                }
            }
        }
    }


    @Override
    public BigInteger CallInRollById(Location location) throws SeminarNotFoundException, ClassesNotFoundException {
       ClassInfo classInfo = classMapper.findClassByClassId(location.getClassInfo().getId());
       if(classInfo == null){
           throw new ClassesNotFoundException();
       }else {
           Seminar seminar = classMapper.findSeminarById(location.getSeminar().getId());
           if(seminar == null){
               throw new SeminarNotFoundException();
           }else{
               Integer b =  classMapper.CallInRollById(location);
               if (b > 0)
               {
                   return location.getId();
               }else {
                   return BigInteger.valueOf(-1);
               }
           }
       }
    }

    @Override
    public List<ClassInfo> listClassByUserId(BigInteger userId) throws IllegalArgumentException, ClassesNotFoundException {
        int a = userId.intValue();
        if(a<=0){
            throw new IllegalArgumentException();
        }else {
            List<CourseSelection> list= classMapper.findClassByUserId(userId);
            if(list == null){
                throw new ClassesNotFoundException();
            }else {
                List<ClassInfo> classes = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    classes.add(classMapper.findClassByClassId(list.get(i).getClassInfo().getId()));
                }
                return classes;
            }
        }

    }

    @Override
    public void endCallRollById(Location location) throws SeminarNotFoundException, ClassesNotFoundException {
        Seminar seminar = classMapper.findSeminarById(location.getSeminar().getId());
        if(seminar == null){
            throw new SeminarNotFoundException();
        }else{
            ClassInfo classInfo = classMapper.findClassByClassId(location.getClassInfo().getId());
            if(classInfo == null){
                throw new ClassesNotFoundException();
            }else{
                classMapper.endCallRollById(location);
            }
        }
    }


    @Override
    public void deleteCoueseSelectionByClassId(BigInteger classId) throws ClassesNotFoundException {
        ClassInfo classInfo = classMapper.findClassByClassId(classId);
        if(classInfo == null){
            throw new ClassesNotFoundException();
        }else{
            classMapper.deleteCoueseSelectionByClassId(classId);
        }

    }
}

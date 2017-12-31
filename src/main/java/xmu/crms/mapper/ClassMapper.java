package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

@Component
@Mapper
public interface ClassMapper {

    Boolean deleteClassSelectionByClassId(BigInteger classId);

    User findTeacherIdByteacherName(String teacherName);

    Course findCourseIdByteacherIdAndCourseName(@Param("teacherId") BigInteger teacherId, @Param("courseName") String courseName);

    List<ClassInfo> findClassByCourseId(BigInteger courseId);

    ClassInfo findClassByClassId(BigInteger classId);

    Boolean updateClassByClassId(ClassInfo newClass);

    Boolean deleteClassByClassId(BigInteger classId);

    Integer insertCourseSelectionById(CourseSelection courseSelection);

    User findStudentdByStudentId(BigInteger studentId);

    User findTeacherByTeacherId(BigInteger teacherId);

    Course findCourseByCourseId(BigInteger courseId);

    BigInteger findCourseIdByUserIdAndClassId(@Param("userId") BigInteger userId, @Param("classId") BigInteger classId);

    Boolean deleteCourseSelectionById(@Param("userId") BigInteger userId, @Param("classId") BigInteger classId);

    Location getCallStatusById(@Param("classId") BigInteger classId, @Param("seminarId") BigInteger seminarId);

    Seminar findSeminarById(BigInteger semiarId);

    Integer insertClassById(ClassInfo classInfo);

    void deleteClassByCourseId(BigInteger courseId);


    void deleteScoreRuleById(BigInteger classId);

    ClassInfo getScoreRule(BigInteger classId);

    Integer insertScoreRule(ClassInfo classInfo);

    Integer CallInRollById(Location location);

    List<CourseSelection> findClassByUserId(BigInteger userId);

    void endCallRollById(Location location);

    void deleteCoueseSelectionByClassId(BigInteger classId);

}

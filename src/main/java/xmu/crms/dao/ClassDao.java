package xmu.crms.dao;

import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Location;
import xmu.crms.exception.*;

import java.math.BigInteger;
import java.util.List;

public interface ClassDao {

    Boolean deleteClassSelectionByClassId(BigInteger classId);

    List<ClassInfo> listClassByName(String courseName, String teacherName) throws
            UserNotFoundException,CourseNotFoundException,ClassesNotFoundException;

    List<ClassInfo> findClassByCourseId(BigInteger courseId) throws
            CourseNotFoundException;

    ClassInfo findClassByClassId(BigInteger classId) throws
            ClassesNotFoundException;

    Boolean updateClassByClassId(BigInteger classId, ClassInfo newClass) throws
            ClassesNotFoundException;

    Boolean deleteClassByClassId(BigInteger classId)
            throws ClassesNotFoundException;

    BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId) throws
            UserNotFoundException,ClassesNotFoundException;

    Boolean deleteCourseSelectionById(BigInteger userId, BigInteger classId) throws
            UserNotFoundException,ClassesNotFoundException;


    BigInteger findCourseIdByUserIdAndClassId(BigInteger userId, BigInteger classId);


    Location getCallStatusById(BigInteger classId, BigInteger seminarId) throws SeminarNotFoundException;


    BigInteger insertClassById(BigInteger courseId, ClassInfo classInfo) throws
            CourseNotFoundException;

    void deleteClassByCourseId(BigInteger courseId) throws
            CourseNotFoundException;

    void deleteScoreRuleById(BigInteger classId) throws ClassesNotFoundException;

    ClassInfo getScoreRule(BigInteger classId) throws ClassesNotFoundException;

    BigInteger insertScoreRule(BigInteger classId, ClassInfo proportions)
            throws InvalidOperationException,ClassesNotFoundException;

    BigInteger CallInRollById(Location location)
            throws SeminarNotFoundException,ClassesNotFoundException;

    List<ClassInfo> listClassByUserId(BigInteger userId)
            throws IllegalArgumentException, ClassesNotFoundException;

    void endCallRollById(Location location)
            throws SeminarNotFoundException, ClassesNotFoundException;

    void deleteCoueseSelectionByClassId(BigInteger classId) throws ClassesNotFoundException;
}

package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;
@Component
@Mapper
/**
 * @Author:YellowDragon
 * @Description:
 * @Date:Created in 15:35 2018/1/1 0001
 * @Modified By:
 */
public interface AttendanceMapper {
    /**
     * @Author:YellowDragon
     * @Description:按classid和seminarId获取Attendance
     */
    List<Attendance> ListPresentAttendance(@Param("seminar_id") BigInteger seminarId,@Param("class_id") BigInteger classId);
    /**
     * @Author:YellowDragon
     * @Description:获取该班级总人数
     */
    List<CourseSelection>CountStuNumByClassId(@Param("class_id")BigInteger clssId);
}

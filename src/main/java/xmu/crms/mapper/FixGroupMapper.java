package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;
/**
 * @Author:YellowDragon
 * @Description:
 * @Date:Created in 22:36 2017/12/23 0023
 * @Modified By:
 */
@Mapper
@Component
public interface FixGroupMapper {
/**
 * @Author:YellowDragon
 * @Description:
 * 按班级Id添加固定分组.
 * <p>按ClassId和UserId添加带有队长的固定分组<br>
 * @param classId 固定分组Id
 * @param userId  队长的Id
 */
BigInteger insertFixGroupByClassId(@Param("class_id") BigInteger classId,
                                          @Param("leadeer_id") BigInteger userId);

/**
 * @Author:YellowDragon
 * @Description: 按FixGroupId删除FixGroupMember.
 * <p>按FixGroupId删除FixGroupMember<br>
 * @param fixGroupId 固定分组Id
 */
void deleteFixGroupMemberByFixGroupId(@Param("fix_group_id") BigInteger fixGroupId);

/**
 * @Author:YellowDragon
 * @Description:按FixGroupId和UserId删除FixGroupMember中某个学生.
 * <p>按FixGroupId和UserId删除FixGroupMember中的某个学生<br>
 *
 * @param fixGroupId 固定分组Id
 * @param userId     组员的Id
 */
 void deleteFixGroupUserById(@Param("fix_group_id") BigInteger fixGroupId,@Param("student_id") BigInteger userId);

/**
 * @Author:YellowDragon
 * @Description:查询固定小组成员.
 * ＜p＞按照固定小组id查询该小组的成员<br>
 *
 * @param groupId 要查询的固定小组id
 * @return List 固定小组成员信息
 */
List<User> listFixGroupMemberByGroupId(@Param("fix_group_id") BigInteger groupId);

/**
 * @Author:YellowDragon
 * @Description:按classId查找FixGroup信息.
 * <p>按classId查找FixGroup信息<br>
 *
 * @param classId 班级Id
 * @return List 固定分组列表
 */
List<FixGroup> listFixGroupByClassId(@Param("clas_id") BigInteger classId);

    /**
     * 按classId删除FixGroup
     * <p>先根据classId得到所有的FixGroup信息，再根据FixGroupid删除FixGroupMember表的信息，最后再将FixGroup信息删除<br>*
     *
     * @param classId 班级Id
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @throws ClassesNotFoundException 未找到班级
     * @author zhouzhongjun
     * @see FixGroupService #listFixGroupByClassId(BigInteger classId)
     * @see FixGroupService #deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId)
     */
void deleteFixGroupByClassId(@Param("class_id") BigInteger classId);
    /**
     * 删除固定小组.
     * ＜p＞按照id删除固定小组<br>
     *
     * @param groupId 固定小组的id
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws FixGroupNotFoundException 未找到小组
     * @author YeHongjie
     * @see FixGroupService #deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId)
     */
void deleteFixGroupByGroupId(@Param("group_id") BigInteger groupId);
    /**
     * 修改固定小组.
     * ＜p＞修改固定小组的信息（不包括成员）<br>
     *
     * @param groupId    小组的id
     * @param fixGroupBO 小组信息
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws FixGroupNotFoundException 未找到小组
     * @author YeHongjie
     */
    void updateFixGroupByGroupId(@Param("groupId") BigInteger groupId,@Param("fixGroupBO") FixGroup fixGroupBO);

    /**
     * 查询固定小组.
     * ＜p＞按照id查询某一固定小组的信息（包括成员）<br>
     *
     * @param groupId 小组的id
     * @return List 固定小组成员列表对象，若未找到相关成员返回空(null)
     * @throws IllegalArgumentException        信息不合法，id格式错误
     * @author YeHongjie
     * @see FixGroupService #listFixGroupMemberByGroupId(BigInteger groupId)
     */
    List<FixGroupMember> listFixGroupByGroupId(@Param("fix_group_id") BigInteger groupId);
    /**
     * 将学生加入小组.
     * ＜p＞将用户加入指定的小组<br>
     *
     * @param userId  学生的id
     * @param groupId 要加入小组的id
     * @return BigInteger 若创建成功返回该条记录的id，失败则返回-1
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws FixGroupNotFoundException 未找到小组
     * @throws UserNotFoundException     不存在该学生
     * @throws InvalidOperationException 待添加学生已经在小组里了
     * @author YeHongjie
     */

    BigInteger insertStudentIntoGroup(@Param("student_id") BigInteger userId,@Param("fix_group_id") BigInteger groupId);


    /**
     * 按id获取小组.
     * <p>通过学生id和班级id获取学生所在的班级固定小组<br>
     *
     * @param userId  学生id
     * @param classId 班级id
     * @return FixGroup 返回班级固定小组的信息
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @throws ClassesNotFoundException 未找到小组
     * @throws UserNotFoundException    不存在该学生
     * @author heqi
     * @see UserService#getUserByUserId(BigInteger UserId)
     */
  FixGroup getFixedGroupById(@Param("student_id") BigInteger userId, @Param("class_id") BigInteger classId);
    /**
     * 将固定小组作为讨论课小组名单.
     *
     * @param semianrId    讨论课ID
     * @param fixedGroupId 小组ID
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws FixGroupNotFoundException 未找到小组
     * @throws SeminarNotFoundException  未找到讨论课
     * @author qinlingyun
     */
  Integer fixedGroupToSeminarGroup(@Param("seminar_group_id") BigInteger semianrId,@Param("fix_group_id") BigInteger fixedGroupId);

  void fixedGroupMemberToSeminarGroupMember(@Param("seminar_group_id") BigInteger seminarId, @Param("fix_group_id") BigInteger fixedGroupId);

  FixGroup checkFixGroupById(@Param("fix_group_id") BigInteger fixedGroupId);

}

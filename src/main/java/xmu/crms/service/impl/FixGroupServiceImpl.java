package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.service.*;
import xmu.crms.mapper.*;
import xmu.crms.service.UserService;
import xmu.crms.service.ClassService;
import java.math.BigInteger;
import java.util.List;

@Service
@Repository
@Component
public class FixGroupServiceImpl implements FixGroupService{

    @Autowired(required = false)
    private FixGroupMapper fixGroupMapper;
    private UserService user;
    private ClassService classService;

    /**
     * 按班级Id添加固定分组.
     * <p>按ClassId和UserId添加带有队长的固定分组<br>
     * @param classId 固定分组Id
     * @param userId  队长的Id
     * @return BigInteger 若创建成功返回该条记录的id，失败则返回-1
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @throws UserNotFoundException    不存在该学生
     * @author zhouzhongjun
     */
    @Override
    public BigInteger insertFixGroupByClassId(BigInteger classId, BigInteger userId) throws
            IllegalArgumentException, UserNotFoundException{
        if(user.getUserByUserId(userId)==null) throw new UserNotFoundException();
        return fixGroupMapper.insertFixGroupByClassId(classId,userId);

    }

    /**
     * 按FixGroupId删除FixGroupMember.
     * <p>按FixGroupId删除FixGroupMember<br>
     * @param fixGroupId 固定分组Id
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws FixGroupNotFoundException 未找到小组
     * @author zhouzhongjun
     */
    @Override
    public void deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId) throws
            IllegalArgumentException, FixGroupNotFoundException{
        if(checkFixGroupById(fixGroupId))
        fixGroupMapper.deleteFixGroupMemberByFixGroupId(fixGroupId);
    }

    /**
     * 按FixGroupId和UserId删除FixGroupMember中某个学生.
     * <p>按FixGroupId和UserId删除FixGroupMember中的某个学生<br>
     *
     * @param fixGroupId 固定分组Id
     * @param userId     组员的Id
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws FixGroupNotFoundException 未找到小组
     * @throws UserNotFoundException     不存在该学生
     * @author zhouzhongjun
     */
    @Override
    public void deleteFixGroupUserById(BigInteger fixGroupId, BigInteger userId) throws
            IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException{
        if(checkFixGroupById(fixGroupId)) {
            if (user.getUserByUserId(userId) == null) throw new UserNotFoundException();
            else fixGroupMapper.deleteFixGroupUserById(fixGroupId, userId);
        }

    }

    /**
     * 查询固定小组成员.
     * ＜p＞按照固定小组id查询该小组的成员<br>
     *
     * @param groupId 要查询的固定小组id
     * @return List 固定小组成员信息
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws FixGroupNotFoundException 未找到小组
     * @author YeHongjie
     */
    @Override
    public List<User> listFixGroupMemberByGroupId(BigInteger groupId) throws
            IllegalArgumentException, FixGroupNotFoundException{
        if(checkFixGroupById(groupId))
        if(listFixGroupByGroupId(groupId).size()!=0){
            return fixGroupMapper.listFixGroupMemberByGroupId(groupId);
        }
        return null;
    }

    /**
     * 按classId查找FixGroup信息.
     * <p>按classId查找FixGroup信息<br>
     *
     * @param classId 班级Id
     * @return List 固定分组列表
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @throws ClassesNotFoundException 未找到班级
     * @author zhouzhongjun
     */
    @Override
    public List<FixGroup> listFixGroupByClassId(BigInteger classId) throws
            IllegalArgumentException, ClassesNotFoundException{
        List<FixGroup> list=fixGroupMapper.listFixGroupByClassId(classId);
        if(list==null) {
            throw new ClassesNotFoundException();
        }
        return list;
    }

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
    @Override
    public void deleteFixGroupByClassId(BigInteger classId) throws
            IllegalArgumentException, ClassesNotFoundException{
        ClassInfo classOfFixgroup=classService.getClassByClassId(classId);
        if(classOfFixgroup==null) {
            throw new ClassesNotFoundException();
        }
        List<FixGroup> list=fixGroupMapper.listFixGroupByClassId(classId);
        for(int i=0;i<list.size();i++){
            fixGroupMapper.deleteFixGroupMemberByFixGroupId(list.get(i).getId());
            fixGroupMapper.deleteFixGroupByGroupId(list.get(i).getId());
        }
    }

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
    @Override
    public void deleteFixGroupByGroupId(BigInteger groupId) throws
            IllegalArgumentException, FixGroupNotFoundException{
        if(checkFixGroupById(groupId)){
            fixGroupMapper.deleteFixGroupMemberByFixGroupId(groupId);
            fixGroupMapper.deleteFixGroupByGroupId(groupId);
        }
    }

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
    @Override
    public void updateFixGroupByGroupId(BigInteger groupId, FixGroup fixGroupBO) throws
            IllegalArgumentException, FixGroupNotFoundException{
        if(checkFixGroupById(groupId))fixGroupMapper.updateFixGroupByGroupId(groupId,fixGroupBO);
    }



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
    @Override
    public List<FixGroupMember> listFixGroupByGroupId(BigInteger groupId) throws
            IllegalArgumentException, FixGroupNotFoundException{
        List<FixGroupMember>list=fixGroupMapper.listFixGroupByGroupId(groupId);
        if(list==null) {
            throw new FixGroupNotFoundException();
        }
        return list;
    }

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

    @Override
    public BigInteger insertStudentIntoGroup(BigInteger userId, BigInteger groupId) throws
            IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException,
            InvalidOperationException{
        if(user.getUserByUserId(userId)==null)throw new UserNotFoundException();
        if(checkFixGroupById(groupId)){
            List<FixGroupMember> users=fixGroupMapper.listFixGroupByGroupId(groupId);
            for(int i=0;i<users.size();i++) {
                if (users.get(i).getId().equals(userId)) {
                    throw new InvalidOperationException();
                }
            }
            return fixGroupMapper.insertStudentIntoGroup(userId,groupId);
        }
        return null;

    }

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
    @Override
    public FixGroup getFixedGroupById(BigInteger userId, BigInteger classId) throws
            IllegalArgumentException, ClassesNotFoundException, UserNotFoundException{
        if(classService.getClassByClassId(classId)==null) throw new ClassesNotFoundException();
        else if(user.getUserByUserId(userId)==null)throw new UserNotFoundException();
        else fixGroupMapper.getFixedGroupById(userId,classId);
        return null;
    }


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
    @Override
    public void fixedGroupToSeminarGroup(BigInteger semianrId, BigInteger fixedGroupId) throws
            IllegalArgumentException, FixGroupNotFoundException, SeminarNotFoundException{
        if(checkFixGroupById(fixedGroupId)!=null){
            BigInteger seminarGroupId=BigInteger.valueOf(fixGroupMapper.fixedGroupToSeminarGroup(semianrId,fixedGroupId));
            fixGroupMapper.fixedGroupMemberToSeminarGroupMember(seminarGroupId,fixedGroupId);
        }

    }


    public Boolean checkFixGroupById(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException{
        FixGroup fixGroup=fixGroupMapper.checkFixGroupById(fixGroupId);
        if(fixGroup==null) {
            throw new FixGroupNotFoundException();
        }
        return true;
    }
}



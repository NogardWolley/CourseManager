package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.dao.SeminarGroupDao;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.SeminarGroupMapper;
import xmu.crms.mapper.SeminarGroupMemberMapper;
import xmu.crms.mapper.SeminarGroupTopicMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class SeminarGroupDaoImpl implements SeminarGroupDao {
    @Autowired
    private SeminarGroupMapper seminarGroupMapper;
    @Autowired
    private SeminarGroupMemberMapper seminarGroupMemberMapper;
    @Autowired
    private SeminarGroupTopicMapper seminarGroupTopicMapper;

    @Override
    public void deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId) {
        seminarGroupMemberMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroupId);
    }

    @Override
    public BigInteger insertSeminarGroupMemberById(BigInteger userId, BigInteger groupId) {
        BigInteger id = null;
        int res = seminarGroupMemberMapper.insertSeminarGroupMemberById(id, userId, groupId);
        if (res > 0) {
            return id;
        } else {
            return new BigInteger("-1");
        }
    }

    @Override
    public List<SeminarGroupMember> listSeminarGroupMemberByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
        List<SeminarGroupMember> list = seminarGroupMemberMapper.listSeminarGroupMemberByGroupId(groupId);
        if (list == null) {
            throw new GroupNotFoundException();
        } else {
            return list;
        }
    }


    @Override
    public List<SeminarGroup> listSeminarGroupIdByStudentId(BigInteger userId) throws IllegalArgumentException {
        List<SeminarGroupMember> list = seminarGroupMemberMapper.listSeminarGroupIdByStudentId(userId);

        List<SeminarGroup> groupList = new ArrayList<SeminarGroup>();
        for (SeminarGroupMember s : list) {
            SeminarGroup group = s.getSeminarGroup();
            groupList.add(group);
        }
        return groupList;
    }

    @Override
    public BigInteger getSeminarGroupLeaderByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
        SeminarGroup s = seminarGroupMapper.getSeminarGroupLeaderByGroupId(groupId);
        User leader=s.getLeader();
        if(leader!=null){
        return leader.getId();}
        else{return  null;}
    }

    @Override
    public List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException, SeminarNotFoundException {
        List<SeminarGroup> list = seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
        if (list == null) {
            throw new SeminarNotFoundException();
        } else {
            return list;
        }
    }

    @Override
    public List<SeminarGroup> listSeminarGroupById(BigInteger seminarId, BigInteger classId) throws IllegalArgumentException, SeminarNotFoundException {
        List<SeminarGroup> list=seminarGroupMapper.listSeminarGroupById(seminarId,classId);
        return list;
    }

    @Override
    public void deleteSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
        seminarGroupMapper.deleteSeminarGroupBySeminarId(seminarId);
    }

    @Override
    public BigInteger insertSeminarGroupBySeminarId(BigInteger seminarId, BigInteger classId, SeminarGroup seminarGroup) throws IllegalArgumentException {
        int res = seminarGroupMapper.insertSeminarGroupBySeminarId(seminarId, classId, seminarGroup);
        if (res > 0) {
            return seminarGroup.getId();
        } else {
            return new BigInteger("-1");
        }
    }

    @Override
    public BigInteger insertSeminarGroupMemberByGroupId(BigInteger groupId, SeminarGroupMember seminarGroupMember) {

        int res = seminarGroupMemberMapper.insertSeminarGroupMemberByGroupId(groupId, seminarGroupMember);
        if (res > 0) {
            return seminarGroupMember.getId();
        } else {
            return new BigInteger("-1");
        }
    }

    @Override
    public void deleteSeminarGroupByGroupId(BigInteger seminarGroupId) throws IllegalArgumentException {
        seminarGroupMapper.deleteSeminarGroupByGroupId(seminarGroupId);
    }

    @Override
    public SeminarGroup getSeminarGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
        SeminarGroup seminarGroup = seminarGroupMapper.selectSeminarGroupByGroupId(groupId);
        if (seminarGroup == null) {
            throw new GroupNotFoundException();
        } else {
            return seminarGroup;
        }
    }


    @Override
    public SeminarGroup getSeminarGroupById(BigInteger seminarId, BigInteger userId) throws IllegalArgumentException, GroupNotFoundException {
        SeminarGroup seminarGroup = seminarGroupMapper.getSeminarGroupById(seminarId, userId);
        return seminarGroup;
    }

    @Override
    public List<SeminarGroup> listGroupByTopicId(BigInteger topicId) throws IllegalArgumentException {
        List<SeminarGroup> list = seminarGroupMapper.selectSeminarGroupByTopicId(topicId);
        return list;
    }

    @Override
    public BigInteger insertTopicByGroupId(BigInteger groupId, BigInteger topicId) throws IllegalArgumentException, GroupNotFoundException {
        SeminarGroupTopic seminarGroupTopic = new SeminarGroupTopic();
        Topic topic = new Topic();
        topic.setId(topicId);
        SeminarGroup seminarGroup = new SeminarGroup();
        seminarGroup.setId(groupId);
        seminarGroupTopic.setTopic(topic);
        seminarGroupTopic.setSeminarGroup(seminarGroup);
        int res = seminarGroupTopicMapper.insertTopicByGroupId(seminarGroupTopic);
        if (res > 0) {
            return seminarGroupTopic.getId();
        } else {
            return new BigInteger("-1");
        }
    }

    @Override
    public void updateSeminarGroupById(BigInteger groupId, BigInteger userId) throws IllegalArgumentException, UserNotFoundException, GroupNotFoundException, InvalidOperationException {
        seminarGroupMapper.updateSeminarGroupById(groupId, userId);
    }

    @Override
    public void resignLeaderById(BigInteger groupId, BigInteger userId) throws IllegalArgumentException, UserNotFoundException, GroupNotFoundException, InvalidOperationException {
        seminarGroupMapper.resignLeaderById( groupId,  userId);
    }

    @Override
    public List<SeminarGroup> listSeminarGroupNotHaveTopic(BigInteger seminarId) {
        List<SeminarGroup> list=seminarGroupMapper.selectSeminarGroupNotHaveTopic(seminarId);
        return list;
    }

    @Override
    public void deleteSeminarGroupMemberById(BigInteger seminarGroupId, BigInteger userId) {
        seminarGroupMemberMapper.deleteSeminarGroupMemberById(seminarGroupId,userId);
    }


}

package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;

import java.math.BigInteger;
import java.util.List;

@Component
@Mapper
public interface SeminarGroupMapper {
 SeminarGroup selectSeminarGroupByGroupId(BigInteger seminarGroupId);
 SeminarGroup getSeminarGroupLeaderByGroupId(BigInteger groupId);
 List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId);
 List<SeminarGroup> listSeminarGroupById(BigInteger seminarId, BigInteger classId);
 void deleteSeminarGroupBySeminarId(BigInteger seminarId);
 Integer insertSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId, @Param("classId") BigInteger classId, @Param("seminarGroup") SeminarGroup seminarGroup);
 /**
  * 删除讨论课小组.
  * <p>按照id删除讨论课小组<br>
  * @author YeHongjie
  * @param seminarGroupId 讨论课小组的id
  * @exception IllegalArgumentException 信息不合法，id格式错误
  */
 void deleteSeminarGroupByGroupId(BigInteger seminarGroupId);
SeminarGroup getSeminarGroupById(@Param("seminarId") BigInteger seminarId, @Param("userId") BigInteger userId);
 List<SeminarGroup> selectSeminarGroupByTopicId(BigInteger topicId);
 void updateSeminarGroupById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId);
 /**
  * 新增获取未选择话题的小组方法.
  *
  * @param seminarId
  * @return List 未选择话题小组列表
  */
 List<SeminarGroup> selectSeminarGroupNotHaveTopic(BigInteger seminarId);
 /**
  * 组长辞职.
  * @param groupId
  * @param userId
  */
 void resignLeaderById(BigInteger groupId, BigInteger userId);
}

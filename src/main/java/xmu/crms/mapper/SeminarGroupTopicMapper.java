package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.Topic;

import java.util.List;

/**
 *
 * @author badcode
 * @date 2017/12/20
 *
 */
@Mapper
@Component
public interface SeminarGroupTopicMapper {

    /**
     *
     * 根据seminar和topic获取SeminarGroupTopic
     *
     * @param seminarGroupTopic 存放了seminar和topic信息
     * @return SeminarGroupTopic
     */
    SeminarGroupTopic getBySeminarAndTopic(SeminarGroupTopic seminarGroupTopic);

    /**
     *
     * 根据id删除记录
     *
     * @param seminarGroupTopic
     * @return
     */
    int deleteById(SeminarGroupTopic seminarGroupTopic);

    /**
     *
     * 根据groupId和topicId删除记录
     *
     * @param seminarGroupTopic 存储了group和topic信息
     * @return 删除操作影响行数
     *
     */
    Integer deleteByGroupAndTopic(SeminarGroupTopic seminarGroupTopic);

    /**
     *
     * 根据topicId删除记录
     *
     * @param topic 存储了topic信息
     * @return 删除影响行数
     *
     */
    Integer deleteByTopic(Topic topic);

    /**
     *
     * 根据Id查找记录
     *
     * @param seminarGroupTopic 存储了Id
     * @return 查询结果
     *
     */
    SeminarGroupTopic getById(SeminarGroupTopic seminarGroupTopic);

    /**
     *
     * 查找某个小组的所有topic
     *
     * @param seminarGroup 小组信息
     * @return List<SeminarGroupTopic>
     */
    List<SeminarGroupTopic> listSeminarGroupTopicByGroup(SeminarGroup seminarGroup);

    /**
     *
     * seminarGroup选题
     *
     * @author huhui
     * @param seminarGroupTopic 存储了话题和小组id
     * @return 影响行数
     */
    Integer insertTopicByGroupId(SeminarGroupTopic seminarGroupTopic);

}

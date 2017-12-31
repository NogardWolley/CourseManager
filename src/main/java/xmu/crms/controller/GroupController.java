package xmu.crms.controller;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.*;
import xmu.crms.vo.*;
/**
 * @Author:YellowDragon
 * @Description:
 * @Date:Created in 19:17 2017/12/31 0031
 * @Modified By:
 */

@Controller

@RequestMapping("/group")
public class GroupController {
    @Autowired
    GradeService GradeService;
    @Autowired
    SeminarGroupService seminarGroupService;
    @Autowired
    FixGroupService fixGroupService;
    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;

    /**
     * @Author:YellowDragon
     * @Description:按小组id获取小组详情
     */

    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    @RequestMapping(value = "/{groupId}", method = GET)
    @ResponseBody
    public ResponseEntity getGroupById(@PathVariable int groupId,
                                       @RequestParam(value = "embedTopics",required = false) Boolean embedTopics,
                                       @RequestParam(value = "embedGrade", required = false) Boolean embedGrade) {
        try {
            //把获取到的所有User注入UserVO
            List<User> member = seminarGroupService.listSeminarGroupMemberByGroupId(BigInteger.valueOf(groupId));
            UserVO leader = new UserVO(seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId)).getLeader());
            List<UserVO> members = new ArrayList<UserVO>();
            for (User user : member) {
                UserVO userVO = new UserVO(user);
                members.add(userVO);
            }
            SeminarGroup group = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
            if (embedTopics && embedGrade) {
                List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
                List<TopicVO> topics = new ArrayList<TopicVO>();
                for (SeminarGroupTopic seminarGroupTopic : list) {
                    TopicVO topicVO = new TopicVO(seminarGroupTopic.getTopic().getId(), seminarGroupTopic.getTopic().getName());
                    topics.add(topicVO);
                }
                List<PresentationGradeVO> topicList = new ArrayList<PresentationGradeVO>();
                for (SeminarGroupTopic s : list) {
                    PresentationGradeVO presentationGradeVO = new PresentationGradeVO(s.getTopic().getId().intValue(), group.getPresentationGrade());
                    topicList.add(presentationGradeVO);
                }
                GroupGradeVO groupGradeVO = new GroupGradeVO(topicList, group);
                GroupMemberTopicGradeVO groupDetail = new GroupMemberTopicGradeVO(group.getId().intValue(), group.getId().toString(),
                        leader, members, topics, groupGradeVO, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetail);
            }
            else if (embedTopics && !embedGrade) {
                GroupVO groupVO = new GroupVO(group);
                List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
                List<TopicVO> topics = new ArrayList<TopicVO>();
                for (SeminarGroupTopic seminarGroupTopic : list) {
                    TopicVO topicVO = new TopicVO(seminarGroupTopic.getTopic().getId(), seminarGroupTopic.getTopic().getName());
                    topics.add(topicVO);
                }
                GroupDetailsVO groupDetailsVO = new GroupDetailsVO(groupVO, leader, members, topics, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetailsVO);
            }
            else if (!embedTopics && embedGrade) {
                //获得小组所有的topicId和分数放入PresentationGradeVO
                List<PresentationGradeVO> topicList = new ArrayList<PresentationGradeVO>();
                List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
                for (SeminarGroupTopic s : list) {
                    PresentationGradeVO presentationGradeVO = new PresentationGradeVO(s.getTopic().getId().intValue(), group.getPresentationGrade());
                    topicList.add(presentationGradeVO);
                }

                GroupGradeVO groupGradeVO = new GroupGradeVO(topicList, group);
                GroupMemberGradeVO groupDetail = new GroupMemberGradeVO(group.getId().intValue(), group.getId().toString(),
                        leader, members, groupGradeVO, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetail);
            } else {
                GroupMembersVO groupMembersVO = new GroupMembersVO(group.getId().intValue(),
                        group.getId().toString(), leader, members, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupMembersVO);
            }
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * @Author:YellowDragon
     * @Description:
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{groupId}/resign", method = PUT)
    @ResponseBody
    public ResponseEntity resignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
        try {
            User user = userService.getUserByUserId(BigInteger.valueOf(request.get("id")));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
        try {
            seminarGroupService.resignLeaderById(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
            return ResponseEntity.status(204).build();
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }catch (InvalidOperationException e){
            e.printStackTrace();
            return ResponseEntity.status(403).build();
        }
    }
    /**
     * @Author:YellowDragon
     * @Description:成为组长
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{groupId}/assign", method = PUT)
    @ResponseBody
    public ResponseEntity asignGroupLeader(@PathVariable int groupId,@RequestBody Map<String,Integer>request){
        try{
            seminarGroupService.assignLeaderById(BigInteger.valueOf(groupId),BigInteger.valueOf(request.get("id")));
            return ResponseEntity.status(204).build();
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }catch (GroupNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }catch (InvalidOperationException e){
            e.printStackTrace();
            return ResponseEntity.status(409).build();
        }
    }
    /**
     * @Author:YellowDragon
     * @Description:添加成员
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{groupId}/add",method = PUT)
    @ResponseBody
    public ResponseEntity addGroupMember(@PathVariable int groupId,@RequestBody Map<String,Integer>request){
        try {
            seminarGroupService.insertSeminarGroupMemberById(BigInteger.valueOf(request.get("id")),BigInteger.valueOf(groupId));
            return ResponseEntity.status(204).build();
        }catch (GroupNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }catch (InvalidOperationException e){
            e.printStackTrace();
            return ResponseEntity.status(403).build();
        }
    }
    /**
     * @Author:YellowDragon
     * @Description:移除组员
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{groupId}/remove",method = PUT)
    @ResponseBody
    public ResponseEntity removeGroupMember(@PathVariable int groupId,@RequestBody Map<String,Integer>request){
            seminarGroupService.deleteSeminarGroupMemberById(BigInteger.valueOf(groupId),BigInteger.valueOf(request.get("id")));
        return ResponseEntity.status(204).build();
//        try {
//            fixGroupService.deleteFixGroupUserById(BigInteger.valueOf(groupId),BigInteger.valueOf(request.get("id")));
//            return ResponseEntity.status(204).build();
//        } catch (UserNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(400).build();
//        } catch (FixGroupNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(404).build();
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return ResponseEntity.status(403).build();
//        }
    }

    /**
     * @Author:YellowDragon
     * @Description:小组按Id选择话题
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{groupId}/topic",method = POST)
    @ResponseBody
    public ResponseEntity chooseTopic(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
        try {
            seminarGroupService.insertTopicByGroupId(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
            Map<String, String> result = new HashMap<String, String>();
            String url = "/group/" + String.valueOf(groupId) + "/topic/" + String.valueOf(request.get("id"));
            result.put("url", url);
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }

    /**
     * @Author:YellowDragon
     * @Description:小组按Id取消选择话题
     */
    @PreAuthorize("hasRole('STUDENT')")
    @RequestMapping(value = "/{groupId}/topic/{topicId}",method =DELETE)
    @ResponseBody
    public ResponseEntity deleteGroupTopic(@PathVariable int groupId,@PathVariable int topicId,Map<String,Integer>request){
        try{
            topicService.deleteSeminarGroupTopicById(BigInteger.valueOf(groupId),BigInteger.valueOf(topicId));
            return ResponseEntity.status(204).build();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.status(403).build();
        }
    }

    /**
     * @Author:YellowDragon
     * @Description:按ID获取小组的讨论课成绩
     */
    @PreAuthorize("hasRole('STUDENT')or hasRole('TEACHER')")
    @RequestMapping(value = "/{groupId}/grade",method =GET)
    @ResponseBody
    public ResponseEntity getGradeByGroupId(@PathVariable int groupId) {
        try {
            SeminarGroup seminarGroup = GradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
            List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
            List<PresentationGradeVO> topicList = new ArrayList<PresentationGradeVO>();
            for (SeminarGroupTopic s : list) {
                PresentationGradeVO presentationGradeVO = new PresentationGradeVO(s.getTopic().getId().intValue(), seminarGroup.getPresentationGrade());
                topicList.add(presentationGradeVO);
            }
            GroupGradeVO groupGradeVO = new GroupGradeVO(topicList, seminarGroup);
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupGradeVO);
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }
        /**
         * @Author:YellowDragon
         * @Description:按ID设置小组的报告分
         */
        @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
        @RequestMapping(value = "/{groupId}/grade/report", method = PUT)
        @ResponseBody
        public ResponseEntity finalGradeByGroupId(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
            try {
                SeminarGroup seminarGroup = GradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
                BigInteger grade = BigInteger.valueOf(request.get("reportGrade"));
                GradeService.updateGroupByGroupId(BigInteger.valueOf(groupId), grade);
                return ResponseEntity.status(204).build();
            } catch (GroupNotFoundException e) {
                e.printStackTrace();
                return ResponseEntity.status(404).build();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return ResponseEntity.status(400).build();
            }
        }
        /**
         * @Author:YellowDragon
         * @Description:提交对其他小组的打分
         */
        @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
        @RequestMapping(value = "/{groupId}/grade/presentation/{studentId}", method = PUT)
        @ResponseBody
        public ResponseEntity submitGradeByGroupId(@PathVariable int groupId, @PathVariable int studentId,
                                                   @RequestBody List<PresentationGradeVO> presentationGrade) {
            try {
                SeminarGroup seminarGroup = GradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
                for (PresentationGradeVO presentation : presentationGrade) {
                    GradeService.insertGroupGradeByUserId(BigInteger.valueOf(presentation.getTopicId()),
                            BigInteger.valueOf(studentId), BigInteger.valueOf(groupId),
                            BigInteger.valueOf(presentation.getGrade()));
                }
                return ResponseEntity.status(204).build();
            } catch (GroupNotFoundException e) {
                e.printStackTrace();
                return ResponseEntity.status(404).build();
            }
        }
}

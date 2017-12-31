package xmu.crms.dto;
import java.util.ArrayList;
public class GroupVO {
	Integer id;
	String name;
	UserVO leader;
	ArrayList<UserVO> members;
	TopicVO topics;
	String report;
	SeminarGradeVO grade;
	public GroupVO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public GroupVO(Integer id, String name, UserVO leader, ArrayList<UserVO> members, TopicVO topics) {
		super();
		this.id = id;
		this.name = name;
		this.leader = leader;
		this.members = members;
		this.topics = topics;
	}
	public GroupVO(Integer id, String name, TopicVO topics) {
		super();
		this.id = id;
		this.name = name;
		this.topics = topics;
	}
	public GroupVO(Integer id, TopicVO topics) {
		super();
		this.id = id;
		this.topics = topics;
	}
	public GroupVO(Integer id, UserVO leader, ArrayList<UserVO> members, TopicVO topics, String report) {
		super();
		this.id = id;
		this.leader = leader;
		this.members = members;
		this.topics = topics;
		this.report = report;
	}
	public GroupVO(Integer id) {
		super();
		this.id = id;
	}
	public GroupVO(Integer id, UserVO leader, ArrayList<UserVO> members) {
		super();
		this.id = id;
		this.leader = leader;
		this.members = members;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserVO getLeader() {
		return leader;
	}
	public void setLeader(UserVO leader) {
		this.leader = leader;
	}
	
	
	public ArrayList<UserVO> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<UserVO> members) {
		this.members = members;
	}
	public TopicVO getTopics() {
		return topics;
	}
	public void setTopics(TopicVO topics) {
		this.topics = topics;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public SeminarGradeVO getGrade() {
		return grade;
	}
	public void setGrade(SeminarGradeVO grade) {
		this.grade = grade;
	}

}

package xmu.crms.dto;
import java.util.ArrayList;
public class presentationGrade {
	Integer topicId;
	Integer grade;
	public presentationGrade(Integer topicId, Integer grade) {
		super();
		this.topicId = topicId;
		this.grade = grade;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}

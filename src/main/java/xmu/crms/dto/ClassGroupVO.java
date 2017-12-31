package xmu.crms.dto;
import java.util.ArrayList;
public class ClassGroupVO {
	StudentVO leader;
	ArrayList<StudentVO> menber;
	TopicVO topicVO;

	public TopicVO getTopicVO() {
		return topicVO;
	}

	public ClassGroupVO(StudentVO leader, ArrayList<StudentVO> menber) {
		super();
		this.leader = leader;
		this.menber = menber;
	}
	public StudentVO getLeader() {
		return leader;
	}
	public void setLeader(StudentVO leader) {
		this.leader = leader;
	}
	public ArrayList<StudentVO> getMenber() {
		return menber;
	}
	public void setMenber(ArrayList<StudentVO> menber) {
		this.menber = menber;
	}


}

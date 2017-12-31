package xmu.crms.vo;

import java.util.ArrayList;

public class ClassGroupVO {
	StudentOrignalVO leader;
	ArrayList<StudentOrignalVO> menber;
	TopicOrignalVO topicOrignalVO;

	public TopicOrignalVO getTopicOrignalVO() {
		return topicOrignalVO;
	}

	public ClassGroupVO(StudentOrignalVO leader, ArrayList<StudentOrignalVO> menber) {
		super();
		this.leader = leader;
		this.menber = menber;
	}
	public StudentOrignalVO getLeader() {
		return leader;
	}
	public void setLeader(StudentOrignalVO leader) {
		this.leader = leader;
	}
	public ArrayList<StudentOrignalVO> getMenber() {
		return menber;
	}
	public void setMenber(ArrayList<StudentOrignalVO> menber) {
		this.menber = menber;
	}


}

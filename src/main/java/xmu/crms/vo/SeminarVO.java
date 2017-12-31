package xmu.crms.vo;

import java.util.ArrayList;

public class SeminarVO {
	Integer id;
	String name;
	String description;
	String CourseName;
	String groupingMethod;
	String startTime;
	String endTime;
	Integer grade;
	ArrayList<ClassVO> classVOS;
	ProportionVO proportions;
	ArrayList<TopicVO> topics;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCourseName() {
		return CourseName;
	}

	public String getGroupingMethod() {
		return groupingMethod;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public Integer getGrade() {
		return grade;
	}

	public ArrayList<ClassVO> getClassVOS() {
		return classVOS;
	}

	public ProportionVO getProportions() {
		return proportions;
	}

	public ArrayList<TopicVO> getTopics() {
		return topics;
	}
}

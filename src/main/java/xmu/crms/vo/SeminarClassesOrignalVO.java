package xmu.crms.vo;

import java.util.ArrayList;

public class SeminarClassesOrignalVO {
	Integer id;
	String name;
	String courseName;
	String startTime;
	String endTime;
	ArrayList<ClassOrignalVO> classOrignalVOS;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public ArrayList<ClassOrignalVO> getClassOrignalVOS() {
		return classOrignalVOS;
	}
	public void setClassOrignalVOS(ArrayList<ClassOrignalVO> classOrignalVOS) {
		this.classOrignalVOS = classOrignalVOS;
	}

}

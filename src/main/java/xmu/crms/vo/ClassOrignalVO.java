package xmu.crms.vo;

public class ClassOrignalVO {
	Integer id;
	String name;
	Integer numStudent;
	String time;
	String site;
	Integer calling;
	String roster;
	String CourseName;
	String TeacherName;
	ProportionVO propottions;
	public ClassOrignalVO(Integer id, String name, Integer numStudent, String time, String site, Integer calling, String roster,
                          ProportionVO propottions) {
		super();
		this.id = id;
		this.name = name;
		this.numStudent = numStudent;
		this.time = time;
		this.site = site;
		this.calling = calling;
		this.roster = roster;
		this.propottions = propottions;
	}
	public ClassOrignalVO(Integer id, String name, Integer numStudent, String time, String site, String courseName,
                          String teacherName) {
		super();
		this.id = id;
		this.name = name;
		this.numStudent = numStudent;
		this.time = time;
		this.site = site;
		CourseName = courseName;
		TeacherName = teacherName;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public ClassOrignalVO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public ClassOrignalVO(Integer id, String name, String teacherName) {
		super();
		this.id = id;
		this.name = name;
		TeacherName = teacherName;
	}
	public String getTeacherName() {
		return TeacherName;
	}
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}
	public ProportionVO getPropottions() {
		return propottions;
	}
	public void setPropottions(ProportionVO propottions) {
		this.propottions = propottions;
	}
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
	public Integer getNumStudent() {
		return numStudent;
	}
	public void setNumStudent(Integer numStudent) {
		this.numStudent = numStudent;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Integer getCalling() {
		return calling;
	}
	public void setCalling(Integer calling) {
		this.calling = calling;
	}
	public String getRoster() {
		return roster;
	}
	public void setRoster(String roster) {
		this.roster = roster;
	}
	

}

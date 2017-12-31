package xmu.crms.dto;

public class CourseClassVO {
	Integer id;
	String name;
	Integer numStudent;
	String time;
	String site;
	String courseName;
	TeacherVO courseTeacherVO;
	public CourseClassVO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public TeacherVO getCourseTeacherVO() {
		return courseTeacherVO;
	}
	public void setCourseTeacherVO(TeacherVO courseTeacherVO) {
		this.courseTeacherVO = courseTeacherVO;
	}
}

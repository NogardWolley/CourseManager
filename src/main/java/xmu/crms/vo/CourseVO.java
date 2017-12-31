package xmu.crms.vo;

public class CourseVO {
	Integer id;
	UserVO teacher;
	String name;
	Integer numClass;
	Integer numStudent;
	String starTime;
	String endTime;
	String description;
	ProportionVO proportions;

	public CourseVO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CourseVO(Integer id, String name, Integer numClass, Integer numStudent, String starTime, String endTime) {
		super();
		this.id = id;
		this.name = name;
		this.numClass = numClass;
		this.numStudent = numStudent;
		this.starTime = starTime;
		this.endTime = endTime;
	}
	public CourseVO(Integer id, String name, String description, UserVO teacher) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.name = name;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserVO getTeacher() {
		return teacher;
	}
	public void setTeacher(UserVO teacher) {
		this.teacher = teacher;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumClass() {
		return numClass;
	}
	public void setNumClass(Integer numClass) {
		this.numClass = numClass;
	}
	public Integer getNumStudent() {
		return numStudent;
	}
	public void setNumStudent(Integer numStudent) {
		this.numStudent = numStudent;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProportionVO getProportions() {
		return proportions;
	}
	public void setProportions(ProportionVO proportions) {
		this.proportions = proportions;
	}
	

}

package xmu.crms.vo;

public class SeminarDetailOrignalVO {
	Integer id;
	String name;
	String site;
	String startTime;
	String endTime;
	String teacherName;
	String teacherEmail;
	public SeminarDetailOrignalVO(Integer id, String name, String site, String startTime, String endTime, String teacherName,
                                  String teacherEmail) {
		super();
		this.id = id;
		this.name = name;
		this.site = site;
		this.startTime = startTime;
		this.endTime = endTime;
		this.teacherName = teacherName;
		this.teacherEmail = teacherEmail;
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
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
}

package xmu.crms.vo;

public class StudentSeminarOrignalVO {
	Integer id;
	String name;
	String groupingMethod;
	String courseName;
	String startTime;
	String endTime;
	Integer classCalling;
	boolean isLeader;
	boolean areTopicsSeletced;
	public StudentSeminarOrignalVO(Integer id, String name, String groupingMethod, String courseName, String startTime,
                                   String endTime, Integer classCalling, boolean isLeader, boolean areTopicsSeletced) {
		super();
		this.id = id;
		this.name = name;
		this.groupingMethod = groupingMethod;
		this.courseName = courseName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.classCalling = classCalling;
		this.isLeader = isLeader;
		this.areTopicsSeletced = areTopicsSeletced;
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
	public String getGroupingMethod() {
		return groupingMethod;
	}
	public void setGroupingMethod(String groupingMethod) {
		this.groupingMethod = groupingMethod;
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
	public Integer getClassCalling() {
		return classCalling;
	}
	public void setClassCalling(Integer classCalling) {
		this.classCalling = classCalling;
	}
	public boolean isLeader() {
		return isLeader;
	}
	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	public boolean isAreTopicsSeletced() {
		return areTopicsSeletced;
	}
	public void setAreTopicsSeletced(boolean areTopicsSeletced) {
		this.areTopicsSeletced = areTopicsSeletced;
	}
	

}

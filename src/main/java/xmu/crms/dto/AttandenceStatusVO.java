package xmu.crms.dto;
import java.util.ArrayList;
public class AttandenceStatusVO {
	Integer numPresent;
	Integer numStudent;
	String status;
	String group;
	public AttandenceStatusVO(Integer numPresent, Integer numStudent, String status, String group) {
		super();
		this.numPresent = numPresent;
		this.numStudent = numStudent;
		this.status = status;
		this.group = group;
	}
	public Integer getNumPresent() {
		return numPresent;
	}
	public void setNumPresent(Integer numPresent) {
		this.numPresent = numPresent;
	}
	public Integer getNumStudent() {
		return numStudent;
	}
	public void setNumStudent(Integer numStudent) {
		this.numStudent = numStudent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
}

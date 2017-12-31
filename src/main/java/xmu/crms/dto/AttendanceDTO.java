package xmu.crms.dto;
import java.util.ArrayList;
public class AttendanceDTO {
	Integer numPresent;
	ArrayList<UserVO> present;
	ArrayList<UserVO> late;
	ArrayList<UserVO> absent;
	public AttendanceDTO(Integer numPresent, ArrayList<UserVO> present) {
		super();
		this.numPresent = numPresent;
		this.present = present;
	}
	
	public AttendanceDTO(Integer numPresent, ArrayList<UserVO> present, ArrayList<UserVO> late,
                         ArrayList<UserVO> absent) {
		
		this.numPresent = numPresent;
		this.present = present;
		this.late = late;
		this.absent = absent;
	}

	public ArrayList<UserVO> getLate() {
		return late;
	}
	public void setLate(ArrayList<UserVO> late) {
		this.late = late;
	}
	public Integer getNumPresent() {
		return numPresent;
	}
	public void setNumPresent(Integer numPresent) {
		this.numPresent = numPresent;
	}
	public ArrayList<UserVO> getPresent() {
		return present;
	}
	public void setPresent(ArrayList<UserVO> present) {
		this.present = present;
	}

	public ArrayList<UserVO> getAbsent() {
		return absent;
	}

	public void setAbsent(ArrayList<UserVO> absent) {
		this.absent = absent;
	}

}

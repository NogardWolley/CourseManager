package xmu.crms.vo;
import java.math.BigInteger;
import java.util.ArrayList;
public class AttendanceListOrignalVO {
	BigInteger id;
	String name;
	public AttendanceListOrignalVO(){}
	public AttendanceListOrignalVO(BigInteger id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

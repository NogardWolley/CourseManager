package xmu.crms.vo;

public class SchoolOrignalVO {
	Integer id;
	String name;
	String province;
	String city;
	public SchoolOrignalVO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public SchoolOrignalVO(Integer id, String name, String province, String city) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
		this.city = city;
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
	public SchoolOrignalVO(Integer id) {
		super();
		this.id = id;
	}
	
	
	

}

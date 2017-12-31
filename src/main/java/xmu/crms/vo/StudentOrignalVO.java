package xmu.crms.vo;

public class StudentOrignalVO {
	Integer id;
	String type;
	String number;
	String name;
	String phone;
	String email;
	String gender;
	SchoolOrignalVO school;
	String title;
	String password;
	String unionID;
	String avatar;
	public StudentOrignalVO(Integer id) {
		super();
		this.id = id;
	}
	public StudentOrignalVO(Integer id, String type, String number, String name, String phone, String email, String gender,
							SchoolOrignalVO school, String title, String password, String unionID, String avatar) {
		super();
		this.id = id;
		this.type = type;
		this.number = number;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.school = school;
		this.title = title;
		this.password = password;
		this.unionID = unionID;
		this.avatar = avatar;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public SchoolOrignalVO getSchool() {
		return school;
	}
	public void setSchool(SchoolOrignalVO school) {
		this.school = school;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUnionID() {
		return unionID;
	}
	public void setUnionID(String unionID) {
		this.unionID = unionID;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}

package xmu.crms.vo;

public class UserVO {
	Integer id;
	String type;
	String name;
	String number;
	String phone;
	String email;
	String gender;
	SchoolVO school;
	String title;
	String password;
	String unionID;
	String avatar;

	public Integer getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public SchoolVO getSchool() {
		return school;
	}

	public String getTitle() {
		return title;
	}

	public String getPassword() {
		return password;
	}

	public String getUnionID() {
		return unionID;
	}

	public String getAvatar() {
		return avatar;
	}
}

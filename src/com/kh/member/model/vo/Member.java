package com.kh.member.model.vo;

import java.sql.Date;
 
public class Member {
// 필드부
	// DB의 Member테이블에 정의되어 있는 컬럼들을 필드로 정의
		// MEMBER_ID, MEMBER_PWD, MEMBER_NAME, GENDER, 
		// AGE, EMAIL, PHONE, ADDRESS, HOBBY, ENROLL_DATE
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
// 생성자부 
	public Member() {}
	public Member(String memberId, String memberPwd, String memberName,
			char gender, int age, String email, String phone, String address,
			String hobby, Date enrollDate) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
		
	}

// 메소드부
	// Getter/Setter 메소드
	public String getMemberId() {		return memberId;	}
	public void setMemberId(String memberId) {		this.memberId = memberId;	}
	public String getMemberPwd() {		return memberPwd;	}
	public void setMemberPwd(String memberPwd) {		this.memberPwd = memberPwd;	}
	public String getMemberName() {		return memberName;	}
	public void setMemberName(String memberName) {		this.memberName = memberName;	}
	public char getGender() {		return gender;	}
	public void setGender(char gender) {		this.gender = gender;	}
	public int getAge() {		return age;	}
	public void setAge(int age) {		this.age = age;	}
	public String getEmail() {		return email;	}
	public void setEmail(String email) {		this.email = email;	}
	public String getPhone() {		return phone;	}
	public void setPhone(String phone) {		this.phone = phone;	}
	public String getAddress() {		return address;	}
	public void setAddress(String address) {		this.address = address;	}
	public String getHobby() {		return hobby;	}
	public void setHobby(String hobby) {		this.hobby = hobby;	}
	public Date getEnrollDate() {		return enrollDate;	}
	public void setEnrollDate(Date enrollDate) {		this.enrollDate = enrollDate;	}
		
	// toString() 메소드 오버라이딩
	@Override
	public String toString() {
		return memberId+" / "+memberPwd+" / "+memberName+" / "+gender+" / "+
				age+" / "+email+" / "+phone+" / "+address+" / "+hobby+" / "+enrollDate;
	}
}
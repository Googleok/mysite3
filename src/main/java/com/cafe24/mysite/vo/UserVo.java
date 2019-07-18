package com.cafe24.mysite.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mysite.validator.constraints.ValidGender;

public class UserVo {
	private Long no;
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name;

	@NotEmpty
	@Email
	private String email;
	
	@Length(min=8, max=16, message="비밀번호 길이는 8~16자 입니다.")
	// 비밀번호 정규식 (최소 8자리에 대문자 1자리 소문자 한자리 숫자 한자리)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "잘못된 비밀번호 형식입니다.")
	private String password;
	 
	@ValidGender
	private String gender;
	
	private String role;
	
	private String joinDate;
	
	
	public UserVo() {
		
	}
	
	public UserVo(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public UserVo(Long no, String name, String email, String password, String gender, String role, String joinDate) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.joinDate = joinDate;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", role=" + role + ", joinDate=" + joinDate + "]";
	}

	
}

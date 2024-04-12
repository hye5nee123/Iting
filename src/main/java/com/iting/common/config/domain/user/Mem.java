package com.iting.common.config.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Mem {
	
	@Id
	@Column(nullable = false)
	private String memNum;
	
	@Column(nullable = false)
	private String id;

	@Column(nullable = false)
	private String mail;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String loginTypCd;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role memFgCd;

	@Builder
	public Mem(String memNum, String mail, String phone, Role role) {
		this.memNum = memNum;
		this.id = 
		this.mail = mail;
		this.phone = phone.replaceAll("-", "");
		this.memFgCd = role;
		this.loginTypCd = "a2";
	}

	public Mem update(String phone) {
		this.phone = phone.replaceAll("-", "");

		return this;
	}

	public String getRoleKey() {
		return this.memFgCd.getKey();
	}
}
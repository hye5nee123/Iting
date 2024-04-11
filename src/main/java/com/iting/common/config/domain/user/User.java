package com.iting.common.config.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.iting.common.config.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role userCd;

    @Builder
    public User(String mail, String phone, Role role) {
        this.mail = mail;
        this.phone = phone;
        this.userCd = role;
    }

    public User update(String phone) {
    	this.phone = phone;

        return this;
    }

    public String getRoleKey() {
        return this.userCd.getKey();
    }
}
package com.iting.common.config.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    MEMBER("B1", "회원"),
	TEACHER("D2", "강사");

    private final String key;
    private final String title;
}
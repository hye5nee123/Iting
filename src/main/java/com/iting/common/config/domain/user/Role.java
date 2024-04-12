package com.iting.common.config.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    b1("B1", "회원"),
	d2("D2", "강사");

    private final String key;
    private final String title;
}
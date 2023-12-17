package com.englishschool.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ADMIN("Директор школы"),
    MANAGER("Преподаватель"),
    CLIENT("Ученик");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}


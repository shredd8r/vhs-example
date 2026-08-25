package com.example.app.user;

import io.vhs.meta.annotation.naming.InstanceName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("Администратор"),
    OPERATOR("Оператор");

    @InstanceName
    private final String role;
}

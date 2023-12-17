package com.englishschool.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    @Test
    public void testGetAuthority() {
        assertEquals("ADMIN", Role.ADMIN.getAuthority());
        assertEquals("MANAGER", Role.MANAGER.getAuthority());
        assertEquals("CLIENT", Role.CLIENT.getAuthority());
    }

    @Test
    public void testGetName() {
        assertEquals("Директор школы", Role.ADMIN.getName());
        assertEquals("Преподаватель", Role.MANAGER.getName());
        assertEquals("Ученик", Role.CLIENT.getName());
    }
}

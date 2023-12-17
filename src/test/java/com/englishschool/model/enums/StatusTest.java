package com.englishschool.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @Test
    public void testGetName() {
        assertEquals("В процессе", Status.IN_PROGRESS.getName());
        assertEquals("Изучено", Status.DONE.getName());
    }
}

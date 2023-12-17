package com.englishschool.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    IN_PROGRESS("В процессе"),
    DONE("Изучено");

    private final String name;
}

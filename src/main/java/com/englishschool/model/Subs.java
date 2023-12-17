package com.englishschool.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Subs {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String file;
    private String spec;
    private byte modules;
    private String time;
    private String complexity;
    @Column(length = 5000)
    private String description;

    public Subs(String name, String file, String spec, byte modules, String time, String complexity, String description) {
        this.name = name;
        this.file = file;
        this.spec = spec;
        this.modules = modules;
        this.time = time;
        this.complexity = complexity;
        this.description = description;
    }
}

package com.englishschool.model;

import com.englishschool.model.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Purchased {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private byte count;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(fetch = FetchType.LAZY)
    private Subs sub;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @OneToOne(fetch = FetchType.LAZY)
    private Teachers teacher;

    public Purchased(Subs sub, Users owner, Teachers teacher) {
        count = 0;
        status = Status.IN_PROGRESS;
        this.sub = sub;
        this.owner = owner;
        this.teacher = teacher;
    }
}

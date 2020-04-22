package com.homebookkeeper.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event extends BaseEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private EventType type;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    private LocalDate date;

    private String description;

    @ManyToOne
    private User user;
}

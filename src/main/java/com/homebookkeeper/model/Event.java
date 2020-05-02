package com.homebookkeeper.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
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

    @NotNull
    @ManyToOne
    private User user;


}

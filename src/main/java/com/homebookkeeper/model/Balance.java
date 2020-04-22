package com.homebookkeeper.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Balance extends BaseEntity{


    private BigDecimal amount;

    @OneToOne
    @MapsId
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CurrencyCodes baseCurrency;
}

package com.homebookkeeper.DTO;

import com.homebookkeeper.model.CurrencyCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BalanceDTO extends BaseDTO {

    private BigDecimal value;

    private CurrencyCodes currency;
}

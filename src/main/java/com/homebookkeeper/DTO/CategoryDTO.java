package com.homebookkeeper.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO extends BaseDTO{

    private String name;

    private BigDecimal capacity;

    private Long userId;
}

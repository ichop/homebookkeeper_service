package com.homebookkeeper.DTO;

import com.homebookkeeper.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO {

    private String name;

    private BigDecimal capacity;

}

package com.homebookkeeper.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    private String email;

    private String name;

    private String password;
}

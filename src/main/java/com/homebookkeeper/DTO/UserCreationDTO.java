package com.homebookkeeper.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserCreationDTO {

    private String name;

    private String password;

    private String email;
}

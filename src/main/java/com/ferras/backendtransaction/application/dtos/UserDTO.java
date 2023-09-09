package com.ferras.backendtransaction.application.dtos;

import com.ferras.backendtransaction.domain.enums.UserType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String password;
    private BigDecimal balance;
    private UserType userType;
}

package com.ferras.backendtransaction.application.dtos;

import com.ferras.backendtransaction.domain.enums.UserType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

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
    @Indexed(unique = true)
    private String document;
    @Indexed(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    private UserType userType;
}

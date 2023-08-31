package com.ferras.backendtransaction.domain.model.user;

import com.ferras.backendtransaction.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Getter
@Setter
@Document
@Data
@AllArgsConstructor
public class User {
    @Id
    private int id;
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

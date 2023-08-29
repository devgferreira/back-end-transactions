package com.ferras.backendtransaction.domain.transaction;

import com.ferras.backendtransaction.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;


@Getter
@Setter
@Document(collection = "transactions")
@Data
@AllArgsConstructor
public class Transaction {
    @Id
    private int id;
    private BigDecimal amount;
    private User sender;
    private User receiver;
}

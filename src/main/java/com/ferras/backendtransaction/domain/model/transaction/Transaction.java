package com.ferras.backendtransaction.domain.model.transaction;

import com.ferras.backendtransaction.domain.model.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Document(collection = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private Long id;
    private BigDecimal amount;
    private User sender;
    private User receiver;
    private LocalDateTime timestamp;
}

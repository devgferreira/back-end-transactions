package com.ferras.backendtransaction.application.dtos;

import com.ferras.backendtransaction.domain.model.user.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO{
        private String id;
        private BigDecimal amount;
        private User sender;
        private User receiver;
        private LocalDateTime timestamp;
    }

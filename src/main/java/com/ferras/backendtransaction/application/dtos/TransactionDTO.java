package com.ferras.backendtransaction.application.dtos;

import com.ferras.backendtransaction.domain.model.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO{
        private Long id;
        private BigDecimal amount;
        private User sender;
        private User receiver;
        private LocalDateTime timestamp;
    }

package com.ferras.backendtransaction.application.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Integer senderId, Integer receiverId) {
}

package com.ferras.backendtransaction.application.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {

    String email;
    String message;
}

package com.ferras.backendtransaction.application.interfaces;

import com.ferras.backendtransaction.application.dtos.UserDTO;
import com.ferras.backendtransaction.domain.model.user.User;

public interface INotificationsService {
    void sendNotifications(UserDTO userDTO, String message) throws Exception;
}

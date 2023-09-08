package com.ferras.backendtransaction.application.service;

import com.ferras.backendtransaction.application.dtos.NotificationDTO;
import com.ferras.backendtransaction.application.dtos.UserDTO;
import com.ferras.backendtransaction.application.interfaces.INotificationsService;
import com.ferras.backendtransaction.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationsService implements INotificationsService {
    @Autowired

    private RestTemplate _restTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public NotificationsService(RestTemplate restTemplate, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void sendNotifications(UserDTO userDTO, String message) throws Exception {
        User user = modelMapper.map(userDTO, User.class);
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);


        ResponseEntity<String> notificationResponse= _restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",
                notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            throw new Exception("Serviço de notificação está fora do ar");
        }
    }
}

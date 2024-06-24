package com.complaint5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.complaint5.dtos.NotificationDto;
import com.complaint5.infra.EmailInfra;

@Service
public class EmailService {
    @Autowired
    private EmailInfra EmailInfra;
    
    @Async
    public void sendEmail(NotificationDto notificationDto){
        this.EmailInfra.sendEmail(notificationDto);
    }
}

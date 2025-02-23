package com.complaint5.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.complaint5.dtos.MessageDto;
import com.complaint5.dtos.NotificationDto;


@FeignClient(value = "email-infra", url = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
public interface EmailInfra {
    @PostMapping
    MessageDto sendEmail(@RequestBody NotificationDto notificationDto);        
}

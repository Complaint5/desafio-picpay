package com.complaint5.infra;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.complaint5.dtos.MessageDto;
import com.complaint5.dtos.TransactionDto;

@FeignClient(value = "authorize-infra", url = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
public interface AuthorizesInfra {
    @PostMapping
    MessageDto getAuthorize(@RequestBody TransactionDto transactionDto);
}

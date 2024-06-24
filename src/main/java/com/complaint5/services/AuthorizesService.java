package com.complaint5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complaint5.dtos.TransactionDto;
import com.complaint5.exceptions.NotAutorizedException;
import com.complaint5.infra.AuthorizesInfra;

@Service
public class AuthorizesService {
    @Autowired
    private AuthorizesInfra autorizaInfra;

    public boolean authorize(TransactionDto transactionDto) {
        if (!this.autorizaInfra.getAuthorize(transactionDto).message().equals("Autorizado")) {
            throw new NotAutorizedException();
        }
        return true;
    }
}

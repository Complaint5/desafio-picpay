package com.complaint5.dtos;

import java.util.UUID;

import com.complaint5.entities.AccountEntity;
import com.complaint5.enums.AccountType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountDto(
        UUID id,
        @NotBlank String name,
        @NotBlank String document,
        @Email String email,
        @Size(min = 8, max = 32) String password,
        String amount,
        AccountType accountType) {

    public AccountDto(AccountEntity accountEntity) {
        this(accountEntity.getId(), accountEntity.getName(), accountEntity.getDocument(), accountEntity.getEmail(),
            accountEntity.getPassword(), accountEntity.getAmount().toString(), accountEntity.getAccountType());
    }
}

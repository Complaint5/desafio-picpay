package com.complaint5.dtos;

import java.util.UUID;

import com.complaint5.entities.TransactionEntity;

import jakarta.validation.constraints.Positive;

public record TransactionDto(
        UUID id,
        @Positive String amount,
        AccountDto payer,
        AccountDto payee) {
    public TransactionDto(TransactionEntity transacaoEntity) {
        this(transacaoEntity.getId(), transacaoEntity.getValor().toString(), new AccountDto(transacaoEntity.getPayer()),
                new AccountDto(transacaoEntity.getPayee()));
    }
}

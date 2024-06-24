package com.complaint5.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complaint5.dtos.AccountDto;
import com.complaint5.dtos.NotificationDto;
import com.complaint5.dtos.TransactionDto;
import com.complaint5.entities.AccountEntity;
import com.complaint5.entities.TransactionEntity;
import com.complaint5.repositories.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transacaoRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthorizesService autorizaService;

    @Transactional
    public TransactionDto transferir(TransactionDto transactionDto) {
        AccountEntity accountPayer = this.accountService.findAccountById(transactionDto.payer().id());
        AccountEntity accountPayee = this.accountService.findAccountById(transactionDto.payee().id());

        this.accountService.payerAutorized(accountPayer, accountPayee, transactionDto.amount());
        this.autorizaService.authorize(transactionDto);

        TransactionEntity transaction = TransactionEntity.builder().valor(new BigDecimal(transactionDto.amount()))
                                                        .payer(accountPayer)
                                                        .payee(accountPayee)
                                                        .date(LocalDateTime.now())
                                                        .build();

        accountPayer.setAmount(accountPayer.getAmount().subtract(new BigDecimal(transactionDto.amount())));
        accountPayee.setAmount(accountPayee.getAmount().add(new BigDecimal(transactionDto.amount())));

        this.transacaoRepository.save(transaction);

        this.emailService.sendEmail(new NotificationDto(new AccountDto(accountPayer), "Transação efetuada com sucesso"));
        this.emailService.sendEmail(new NotificationDto(new AccountDto(accountPayee), "Transação efetuada com sucesso"));

        return new TransactionDto(transaction);
    }

    public List<TransactionDto> findAllTransaction(){
        return this.transacaoRepository.findAll().stream().map((transaction) -> new TransactionDto(transaction)).toList();
    }
}
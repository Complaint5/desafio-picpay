package com.complaint5.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complaint5.dtos.AccountDto;
import com.complaint5.entities.AccountEntity;
import com.complaint5.enums.AccountType;
import com.complaint5.exceptions.DeclinedTransactionException;
import com.complaint5.exceptions.InsufficientFundsException;
import com.complaint5.exceptions.RecordNotFondException;
import com.complaint5.repositories.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public AccountDto creat(AccountDto accountDto) {
        return new AccountDto(this.saveAccount(new AccountEntity(accountDto)));
    }

    @Transactional
    public AccountDto deposit(AccountDto accountDto) {
        AccountEntity account = this.findAccountById(accountDto.id());
        account.setAmount(account.getAmount().add(new BigDecimal(accountDto.amount())));
        return new AccountDto(this.saveAccount(account));
    }

    @Transactional
    public AccountDto withdraw(AccountDto accountDto) {
        AccountEntity account = this.findAccountById(accountDto.id());
        if (account.getAmount().compareTo(new BigDecimal(accountDto.amount())) == 1) {
            account.setAmount(account.getAmount().subtract(new BigDecimal(accountDto.amount())));
            return new AccountDto(this.saveAccount(account));
        }
        throw new InsufficientFundsException();
    }

    @Transactional
    public AccountDto update(AccountDto accountDto) {
        AccountEntity account = this.findAccountById(accountDto.id());
        account.setEmail(accountDto.email());
        account.setPassword(accountDto.password());
        return new AccountDto(this.saveAccount(account));
    }

    public AccountDto findById(UUID id) {
        return new AccountDto(this.findAccountById(id));
    }

    public List<AccountDto> findAll() {
        return this.accountRepository.findAllByActiveTrue().stream().map((account) -> new AccountDto(account)).toList();
    }

    @Transactional
    public void delete(UUID id) {
        AccountEntity account = this.findAccountById(id);
        account.setActive(false);
        this.saveAccount(account);
    }

    @Transactional
    protected AccountEntity saveAccount(AccountEntity accountEntity) {
        return this.accountRepository.save(accountEntity);
    }

    protected AccountEntity findAccountById(UUID id) {
        return accountRepository.findByActiveTrueAndId(id).orElseThrow(() -> new RecordNotFondException());
    }

    protected boolean payerAutorized(AccountEntity accountPayer, AccountEntity accountPayee, String amount) {
        if(accountPayer.equals(accountPayee)){
            throw new DeclinedTransactionException();   
        }
        if (accountPayer.getAccountType().equals(AccountType.MERCHANT)) {
            throw new DeclinedTransactionException();
        }
        if (!(accountPayer.getAmount().compareTo(new BigDecimal(amount)) == 1)) {
            throw new InsufficientFundsException();
        }

        return true;
    }
}

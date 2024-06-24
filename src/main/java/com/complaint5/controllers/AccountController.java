package com.complaint5.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.complaint5.dtos.AccountDto;
import com.complaint5.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> creatAccount(@RequestBody @Valid AccountDto accountDto, UriComponentsBuilder uriBuilder) {
        AccountDto conta = this.accountService.creat(accountDto);
        return ResponseEntity.created(uriBuilder.path("/v1/account/" + conta.id()).build().toUri()).body(conta);
    }

    @PatchMapping("/deposit")
    public ResponseEntity<AccountDto> deposit(@RequestBody @Valid AccountDto accountDto) {
        return ResponseEntity.ok().body(this.accountService.deposit(accountDto));
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<AccountDto> withdraw(@RequestBody @Valid AccountDto accountDto) {
        return ResponseEntity.ok().body(this.accountService.withdraw(accountDto));
    }

    @PutMapping
    public ResponseEntity<AccountDto> updateAccount(@RequestBody @Valid AccountDto accountDto) {
        return ResponseEntity.ok().body(this.accountService.update(accountDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.accountService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount() {
        return ResponseEntity.ok().body(this.accountService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        this.accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

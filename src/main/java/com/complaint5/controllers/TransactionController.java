package com.complaint5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.complaint5.dtos.TransactionDto;
import com.complaint5.services.TransactionService;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    
    @PostMapping
    public ResponseEntity<TransactionDto> transaction(@RequestBody TransactionDto transacaoDto, UriComponentsBuilder uriBuilder){
        TransactionDto transaction = this.transactionService.transferir(transacaoDto);
        return ResponseEntity.created(uriBuilder.path("/v1/transaction/" + transaction.id()).build().toUri()).body(transaction);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> findAllTransaction(){
        return ResponseEntity.ok().body(this.transactionService.findAllTransaction());
    }
}

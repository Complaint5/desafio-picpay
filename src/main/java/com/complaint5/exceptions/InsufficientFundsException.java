package com.complaint5.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(){
        super("Saldo insuficiente");
    }
    
    public InsufficientFundsException(String mensagem){
        super(mensagem);
    }
}

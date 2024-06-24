package com.complaint5.exceptions;

public class DeclinedTransactionException extends RuntimeException {
    public DeclinedTransactionException(){
        super("Transação não autorizada");
    }
    
    public DeclinedTransactionException(String mensagem){
        super(mensagem);
    }
}

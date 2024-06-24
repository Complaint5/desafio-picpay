package com.complaint5.exceptions;

public class NotAutorizedException extends RuntimeException {
    public NotAutorizedException(){
        super("Transação não autorizada");
    }
    
    public NotAutorizedException(String mensagem){
        super(mensagem);
    }
}

package com.complaint5.exceptions;

public class RecordNotFondException extends RuntimeException {
    public RecordNotFondException(){
        super("Registro não encontrado");
    }
    
    public RecordNotFondException(String mensagem){
        super(mensagem);
    }
}

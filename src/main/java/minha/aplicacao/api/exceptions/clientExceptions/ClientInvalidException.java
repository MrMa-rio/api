package minha.aplicacao.api.exceptions.clientExceptions;

public class ClientInvalidException extends RuntimeException{
    public ClientInvalidException(){
        super("CLIENTE INVALIDO!!");
    }
    public ClientInvalidException(String message){
        super(message);
    }
}

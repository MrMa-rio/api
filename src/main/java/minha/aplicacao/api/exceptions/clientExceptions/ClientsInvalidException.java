package minha.aplicacao.api.exceptions.clientExceptions;

public class ClientsInvalidException extends RuntimeException{
    public ClientsInvalidException(){
        super("CLIENTES INVALIDOS!!");
    }
    public ClientsInvalidException(String message){
        super(message);
    }
}

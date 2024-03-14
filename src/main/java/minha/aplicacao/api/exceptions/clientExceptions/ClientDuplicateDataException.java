package minha.aplicacao.api.exceptions.clientExceptions;

public class ClientDuplicateDataException extends RuntimeException{

    public ClientDuplicateDataException(){
        super("CLIENTE JA EXISTENTE!!");
    }
    public ClientDuplicateDataException(String message){ super(message); }
}

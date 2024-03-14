package minha.aplicacao.api.exceptions.clientExceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException() {
        super("CLIENTE NÃO ENCONTRADO!");
    }

}

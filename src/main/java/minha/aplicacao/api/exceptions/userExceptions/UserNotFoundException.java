package minha.aplicacao.api.exceptions.userExceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("USUARIO NÃO ENCONTRADO!");
    }

}

package minha.aplicacao.api.exceptions.userExceptions;

public class UsersNotFoundException extends RuntimeException{

    public UsersNotFoundException(String message) {
        super(message);
    }

    public UsersNotFoundException() {
        super("USUARIOS NÃO ENCONTRADO!");
    }

}

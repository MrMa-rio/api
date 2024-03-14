package minha.aplicacao.api.exceptions.userExceptions;

public class UsersInvalidException extends RuntimeException{
    public UsersInvalidException(){
        super("USUARIOS INVALIDOS!!");
    }
    public UsersInvalidException(String message){
        super(message);
    }
}

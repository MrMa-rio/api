package minha.aplicacao.api.exceptions.userExceptions;

public class UserInvalidException extends RuntimeException{
    public UserInvalidException(){
        super("USUARIO INVALIDO!!");
    }
    public  UserInvalidException(String message){
        super(message);
    }
}

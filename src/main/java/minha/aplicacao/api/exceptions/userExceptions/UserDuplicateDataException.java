package minha.aplicacao.api.exceptions.userExceptions;

public class UserDuplicateDataException extends RuntimeException {

    public UserDuplicateDataException(){
        super("USUARIO JA EXISTENTE!!");
    }
    public UserDuplicateDataException(String message){ super(message); }
}

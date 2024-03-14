package minha.aplicacao.api.exceptions.itemExceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(){
        super("ITEM NAO ENCONTRADO");
    }
    public ItemNotFoundException(String message) {super(message);}
}

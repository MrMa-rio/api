package minha.aplicacao.api.exceptions.itemExceptions;

public class ItemsNotFoundException extends RuntimeException {

    public ItemsNotFoundException(){
        super("NAO HA ITENS");
    }
    public ItemsNotFoundException(String message){super(message);}
}

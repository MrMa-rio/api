package minha.aplicacao.api.exceptions.clientExceptions;

public class ClientsNotFoundException extends RuntimeException{

    public ClientsNotFoundException(String message) {
        super(message);
    }

    public ClientsNotFoundException() {
        super("CLIENTES NÃO ENCONTRADO!");
    }

}

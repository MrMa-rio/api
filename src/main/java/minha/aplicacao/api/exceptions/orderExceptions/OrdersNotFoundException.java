package minha.aplicacao.api.exceptions.orderExceptions;

public class OrdersNotFoundException extends RuntimeException{

    public OrdersNotFoundException(){
        super("PEDIDOS NÃO ENCONTRADO!!");
    }
    public OrdersNotFoundException(String message){
        super(message);
    }
}

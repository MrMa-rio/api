package minha.aplicacao.api.exceptions.orderExceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(){
        super("PEDIDO NÃO ENCONTRADO!!");
    }
    public OrderNotFoundException(String message){
        super(message);
    }
}

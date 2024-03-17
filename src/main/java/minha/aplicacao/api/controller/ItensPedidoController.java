package minha.aplicacao.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"itenspedido","itenspedido/"})
public class ItensPedidoController {

    @GetMapping("/idPedido")
    public void getItensPedido(@PathVariable String idPedido){
        //TODO
    }
}

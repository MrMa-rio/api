package minha.aplicacao.api.controller;

import jakarta.validation.Valid;
import minha.aplicacao.api.DTO.ItensPedido.ItensPedidoDTO;
import minha.aplicacao.api.models.ItensPedido.ItensPedido;
import minha.aplicacao.api.services.ItensPedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping({"itemspedido","itemspedido/"})
public class ItensPedidoController {

    @Autowired
    ItensPedidoServices itensPedidoServices;

    @PostMapping
    public ResponseEntity<?> setItensPedido(@RequestBody @Valid ArrayList<ItensPedidoDTO> itensPedidoDTO){
        ArrayList<ItensPedido> itensPedidos = itensPedidoServices.setItensPedido(itensPedidoDTO);
        return ResponseEntity.ok(itensPedidos);
    }
    @GetMapping("/{idPedido}")
    public ResponseEntity<?> getItensPedido(@PathVariable String idPedido){
        ArrayList<ItensPedido> itensPedidos = itensPedidoServices.getItensByPedido(Integer.valueOf(idPedido));
        return ResponseEntity.ok(itensPedidos);
    }
}

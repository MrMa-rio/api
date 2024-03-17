package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.ItensPedido.ItensPedidoDTO;
import minha.aplicacao.api.repository.IItensPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItensPedidoServices {
    @Autowired
    private IItensPedidoRepository iItensPedidoRepository;

    public void setItensPedido(ItensPedidoDTO[] itensPedidoDTO){
        //TODO
    }

}

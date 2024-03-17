package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.ItensPedido.ItensPedidoDTO;
import minha.aplicacao.api.models.ItensPedido.ItensPedido;
import minha.aplicacao.api.repository.IItensPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItensPedidoServices {
    @Autowired
    private IItensPedidoRepository iItensPedidoRepository;

    public ArrayList<ItensPedido> setItensPedido(ArrayList<ItensPedidoDTO> itensPedidoDTO){
        ArrayList<ItensPedido> itensPedidosArrayList = new ArrayList<>();
        itensPedidoDTO.forEach(itemPedidoDTO -> {
            ItensPedido itensPedido = new ItensPedido(itemPedidoDTO);
            iItensPedidoRepository.save(itensPedido);
            itensPedidosArrayList.add(itensPedido);
        });
        return itensPedidosArrayList;
    }

    public ArrayList<ItensPedido> getItensByPedido(Integer idPedido){
        ArrayList<ItensPedido> itensPedidos = iItensPedidoRepository.findByFkPedido(idPedido);
        return itensPedidos;
    }
}

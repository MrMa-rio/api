package minha.aplicacao.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import minha.aplicacao.api.DTO.ClienteCreateDTO;
import minha.aplicacao.api.DTO.ClienteUpdateDTO;
import minha.aplicacao.api.models.Cliente;
import minha.aplicacao.api.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteServices {
    @Autowired
    private IClienteRepository IClienteRepository;
    public ClienteServices(){
    }
    public Cliente setCliente(ClienteCreateDTO clienteCreateDTO) {
        try {
            Cliente cliente = new Cliente(clienteCreateDTO);
            IClienteRepository.save(cliente);
            return cliente;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> cliente = (ArrayList<Cliente>) IClienteRepository.findAll();

        try {
            return cliente;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public Cliente getClientePorId(Integer idCliente){

        try {
            Optional<Cliente> cliente = IClienteRepository.findById(idCliente);
            return cliente.orElseThrow();
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException(e);
        }
    }
    public Cliente updateCliente(ClienteUpdateDTO clienteUpdateDTO) {
        Cliente cliente = getClientePorId(clienteUpdateDTO.idCliente());
        if(cliente == null){
            return null;
        }
        try{
            cliente.updateCliente(clienteUpdateDTO);
            IClienteRepository.save(cliente);
            return cliente;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    public Cliente deleteLogicalCliente(Integer idCliente){
        Cliente cliente = getClientePorId(idCliente);
        if(cliente == null){
            return null;
        }
        try{
            cliente.deleteCliente();
            IClienteRepository.save(cliente);
            return cliente;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}

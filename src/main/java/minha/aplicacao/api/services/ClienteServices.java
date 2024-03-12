package minha.aplicacao.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import minha.aplicacao.api.DTO.ClienteCreateDTO;
import minha.aplicacao.api.DTO.ClienteUpdateDTO;
import minha.aplicacao.api.models.Cliente;
import minha.aplicacao.api.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ClienteServices {
    @Autowired
    private IClienteRepository IClienteRepository;
    public ClienteServices(){
    }
    public String setUsuario(ClienteCreateDTO clienteCreateDTO) {
        try {
            Cliente cliente = new Cliente(clienteCreateDTO);
            IClienteRepository.save(cliente);
            return cliente.toJson();
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getClientes(){
        ArrayList<Cliente> cliente = (ArrayList<Cliente>) IClienteRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(cliente);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getClientePorId(Integer idCliente){

        try {
            Cliente cliente = IClienteRepository.getReferenceById(idCliente);
            return cliente.toJson();
        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public String updateCliente(ClienteUpdateDTO clienteUpdateDTO) {
        String existeCliente = getClientePorId(clienteUpdateDTO.idCliente());
        if(existeCliente.isEmpty()){
            return null;
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente cliente = objectMapper.readValue(existeCliente, Cliente.class);
            cliente.updateCliente(clienteUpdateDTO);
            IClienteRepository.save(cliente);
            return cliente.toJson();
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
    public String deleteLogicalCliente(Integer idCliente){
        String existeCliente = getClientePorId(idCliente);
        if(existeCliente.isEmpty()){
            return null;
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente cliente = objectMapper.readValue(existeCliente, Cliente.class);
            cliente.deleteCliente();
            IClienteRepository.save(cliente);
            return cliente.toJson();
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}

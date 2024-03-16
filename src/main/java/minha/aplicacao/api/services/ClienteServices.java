package minha.aplicacao.api.services;

import minha.aplicacao.api.DTO.Cliente.ClienteCreateDTO;
import minha.aplicacao.api.DTO.Cliente.ClienteUpdateDTO;
import minha.aplicacao.api.exceptions.clientExceptions.ClientDuplicateDataException;
import minha.aplicacao.api.exceptions.clientExceptions.ClientNotFoundException;
import minha.aplicacao.api.exceptions.clientExceptions.ClientsNotFoundException;
import minha.aplicacao.api.models.Cliente;
import minha.aplicacao.api.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
        } catch (DataIntegrityViolationException e) {
            throw new ClientDuplicateDataException();
        }
    }
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> cliente = (ArrayList<Cliente>) IClienteRepository.findAll();
        if (cliente.isEmpty()) throw new ClientsNotFoundException();
        return cliente;
    }
    public Cliente getClientePorId(Integer idCliente){

        Optional<Cliente> cliente = IClienteRepository.findById(idCliente);
        if(cliente.isEmpty()) throw new ClientNotFoundException();
        return cliente.get();
    }
    public Cliente updateCliente(ClienteUpdateDTO clienteUpdateDTO) {
        Cliente cliente = getClientePorId(clienteUpdateDTO.idCliente());
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
        try{
            cliente.deleteCliente();
            IClienteRepository.save(cliente);
            return cliente;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}

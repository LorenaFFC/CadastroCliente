package com.bank.BankTransaction.service;

import com.bank.BankTransaction.domain.Cliente;
import com.bank.BankTransaction.exception.BadRequestException;
import com.bank.BankTransaction.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {


    public ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listAll(){
     return clienteRepository.findAll();
    }

    public Cliente findById(long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Não Encontrado"));
    }

    public Cliente save(Cliente cliente) {
       return clienteRepository.save(cliente);
    }

    public void delete(long id) {
        clienteRepository.delete(findById(id));
    }

    public void replace(Cliente cliente) {
        delete(cliente.getId());
        save(cliente);
    }
}

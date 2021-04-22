package com.bank.BankTransaction.controller;

import com.bank.BankTransaction.domain.Cliente;
import com.bank.BankTransaction.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> list(){
        return new ResponseEntity<>(clienteService.listAll(), HttpStatus.OK);  }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable long id){
        return new ResponseEntity<>(clienteService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente){
        return new ResponseEntity<>( clienteService.save(cliente), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<Void> deleteCliente(@PathVariable long id){
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public  ResponseEntity<Void> replace(@RequestBody @Valid Cliente cliente){
        clienteService.replace(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

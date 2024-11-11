package es.hotel_back_v2.hotelV2.controllers;

import es.hotel_back_v2.hotelV2.model.Cliente;
import es.hotel_back_v2.hotelV2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //añadir cliente
    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    //eliminar cliente
    @DeleteMapping
    public void delete(@PathVariable String dni) {
        clienteService.deleteCliente(dni);
    }

    //mostrar cliente
    @GetMapping
    public Optional<Cliente> findCliente(@PathVariable String dni) {
        return clienteService.findCliente(dni);
    }

    //modificar cliente
    @PutMapping("/modificarpordni/{dni}")
    public Cliente updateCliente(@PathVariable String dni, @RequestBody Cliente clienteActualizado) {

        return clienteService.updateCliente(dni, clienteActualizado);

    }
}

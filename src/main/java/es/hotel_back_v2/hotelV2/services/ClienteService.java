package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Cliente;
import es.hotel_back_v2.hotelV2.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //añadir cliente
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //eliminar cliente
    @Transactional
    public void deleteCliente(String dni) {

        if (clienteRepository.existsByDni(dni)) {

            clienteRepository.deleteByDni(dni);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    //buscar cliente por dni
    public Optional<Cliente> findCliente(String dni) {
        return clienteRepository.findByDni(dni);
    }

    //modificar cliente usando el DNI
    @Transactional
    public Cliente updateCliente(String dni, Cliente clienteActualizado) {
        Optional <Cliente> cliente = clienteRepository.findByDni(dni);

        if (cliente.isPresent()) {

            cliente.get().setDni(clienteActualizado.getDni());
            cliente.get().setNombre(clienteActualizado.getNombre());
            cliente.get().setApellido(clienteActualizado.getApellido());
            cliente.get().setEmail(clienteActualizado.getEmail());
            cliente.get().setTelefono(clienteActualizado.getTelefono());

            return clienteRepository.save(cliente.get());

        } else {

            throw new RuntimeException("El cliente no existe");
        }
    }
}
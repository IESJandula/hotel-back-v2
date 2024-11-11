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
    public Cliente updateCliente(String dni, Cliente clienteDetails) {
        Optional <Cliente> clienteOptional = clienteRepository.findByDni(dni);

        if (clienteOptional.isPresent()) {

            Cliente cliente = clienteOptional.get();

            cliente.setDni(clienteDetails.getDni());
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setApellido(clienteDetails.getApellido());
            cliente.setEmail(clienteDetails.getEmail());
            cliente.setTelefono(clienteDetails.getTelefono());

            return clienteRepository.save(cliente);

        } else {

            throw new RuntimeException("El cliente no existe");
        }
    }
}
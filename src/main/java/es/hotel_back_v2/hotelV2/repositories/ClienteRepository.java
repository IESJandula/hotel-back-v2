package es.hotel_back_v2.hotelV2.repositories;

import es.hotel_back_v2.hotelV2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //método para verificar si existe un cliente con el dni dado
    boolean existsByDni(String dni);

    //método para encontrar un cliente por DNI
    Optional<Cliente> findByDni(String dni);

    //método para eliminar un cliente por dni
    void deleteByDni(String dni);
}

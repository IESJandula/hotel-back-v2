package es.hotel_back_v2.hotelV2.repositories;

import es.hotel_back_v2.hotelV2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

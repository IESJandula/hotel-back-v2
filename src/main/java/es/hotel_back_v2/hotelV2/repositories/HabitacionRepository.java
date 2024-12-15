package es.hotel_back_v2.hotelV2.repositories;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    Optional<Habitacion> findById(Long id);
}

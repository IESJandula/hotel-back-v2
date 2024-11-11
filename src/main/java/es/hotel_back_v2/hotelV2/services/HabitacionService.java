package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.model.Reserva;
import es.hotel_back_v2.hotelV2.repositories.HabitacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Transactional
    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void eliminarHabitacion(Long numero) {
        habitacionRepository.deleteById(numero);
    }

    public List<Habitacion> buscarHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> buscarHabitacionPorId(Long numero) {
       return habitacionRepository.findById(numero);
    }







}

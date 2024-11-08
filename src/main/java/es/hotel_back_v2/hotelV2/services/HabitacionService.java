package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.repositories.HabitacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Transactional
    public void crearHabitacion(Habitacion habitacion) { return habitacionRepository.save(habitacion);}


}

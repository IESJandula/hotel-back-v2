package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.model.Reserva;
import es.hotel_back_v2.hotelV2.repositories.HabitacionRepository;
import es.hotel_back_v2.hotelV2.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

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

    //Método para encontrar habitaciones ocupadas en una fecha específica
    public List<Habitacion> obtenerHabitacionesOcupadas(Date fecha) {
        //Lista que guardará las habitaciones ocupadas
        List<Habitacion> habitacionesOcupadas = new ArrayList<>();

        //Obtener todas las reservas
        List<Reserva> reservas = reservaRepository.findAll();

        //Recorremos todas las reservas
        for (Reserva reserva : reservas) {
            //Verificamos si la fecha está entre la fecha de inicio y la fecha de fin de la reserva
            if (fecha.after(reserva.getFecha_inicio()) && fecha.before(reserva.getFecha_fin())) {
                //Añadimos todas las habitaciones de esa reserva
                habitacionesOcupadas.addAll(reserva.getHabitaciones());
            }
        }

        //Devolvemos la lista de habitaciones ocupadas
        return habitacionesOcupadas;
    }

}

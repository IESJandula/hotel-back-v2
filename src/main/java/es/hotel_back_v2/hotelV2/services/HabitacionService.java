package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.model.Reserva;
import es.hotel_back_v2.hotelV2.repositories.HabitacionRepository;
import es.hotel_back_v2.hotelV2.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    //crear habitación
    @Transactional
    public Habitacion crearHabitacion(Habitacion habitacion) {
        habitacion.setEstado(Habitacion.Estado.DISPONIBLE); //aseguramos que el estado inicial sea "disponible"
        return habitacionRepository.save(habitacion);
    }

    //eliminar habitación por número
    public void eliminarHabitacion(Long numero) {
        habitacionRepository.deleteById(numero);
    }

    //modificar estado de la habitación
    @Transactional
    public void actualizarEstadoHabitacion(Long numeroHabitacion, Habitacion.Estado estado) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(numeroHabitacion);
        if (habitacion.isPresent()) {
            Habitacion h = habitacion.get();
            h.setEstado(estado);  //establece el estado de la habitación
            habitacionRepository.save(h);
        } else {
            throw new RuntimeException("Habitación no encontrada");
        }
    }

    //mostrar todas las habitaciones
    public List<Habitacion> buscarHabitaciones() {
        return habitacionRepository.findAll();
    }

    //mostrar habitación por número
    public Habitacion buscarPorNumero(Long numero) {
        return habitacionRepository.findById(numero)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
    }

    //obtener habitaciones ocupadas
    public List<Habitacion> obtenerHabitacionesOcupadas(Date fecha) {
        List<Habitacion> habitacionesOcupadas = new ArrayList<>();
        List<Reserva> reservas = reservaRepository.findAll();

        for (Reserva reserva : reservas) {
            if (fecha.after(reserva.getFecha_inicio()) && fecha.before(reserva.getFecha_fin())) {
                habitacionesOcupadas.addAll(reserva.getHabitaciones());
            }
        }

        return habitacionesOcupadas;
    }

    @Transactional
    public Habitacion modificarHabitacion(Long numero, Habitacion habitacionActualizada) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(numero);
        if (habitacion.isPresent()) {
            Habitacion h = habitacion.get();
            h.setTipo(habitacionActualizada.getTipo());
            h.setPrecio(habitacionActualizada.getPrecio());
            h.setEstado(habitacionActualizada.getEstado());
            h.setCapacidad(habitacionActualizada.getCapacidad());
            return habitacionRepository.save(h);
        } else {
            throw new RuntimeException("Habitación no encontrada");
        }
    }
}
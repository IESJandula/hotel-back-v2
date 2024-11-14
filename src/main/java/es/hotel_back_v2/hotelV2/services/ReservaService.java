package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Cliente;
import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.model.Reserva;
import es.hotel_back_v2.hotelV2.repositories.ClienteRepository;
import es.hotel_back_v2.hotelV2.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Reserva crearReserva(Reserva reserva) {
        //Verificar si el cliente existe usando el DNI
        Optional<Cliente> cliente = clienteRepository.findByDni(reserva.getCliente().getDni()); //Buscar cliente por DNI

        //Si no encuentra al cliente, lanza una excepción
        if (!cliente.isPresent()) {
            throw new RuntimeException("El cliente no existe");
        }

        //Verificar si hay habitaciones disponibles
        if (hayHabitacionesDisponibles(reserva.getHabitaciones(), reserva.getFecha_inicio(), reserva.getFecha_fin())) {
            return reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("No hay habitaciones disponibles en las fechas seleccionadas");
        }
    }

    public boolean hayHabitacionesDisponibles(List<Habitacion> habitacionesSolicitadas, Date fechaInicio, Date fechaFin) {
        List<Reserva> todasReservas = reservaRepository.findAll();

        for (Habitacion habitacion : habitacionesSolicitadas) {
            for (Reserva reserva : todasReservas) {
                if (reserva.getHabitaciones().contains(habitacion)) {
                    //Convertimos las fechas a milisegundos para hacer la comparación
                    long inicioExistente = reserva.getFecha_inicio().getTime();
                    long finExistente = reserva.getFecha_fin().getTime();
                    long nuevoInicio = fechaInicio.getTime();
                    long nuevoFin = fechaFin.getTime();

                    //Si las fechas coinciden, devolvemos que no hay disponibilidad
                    if ((nuevoInicio <= finExistente) && (nuevoFin >= inicioExistente)) {
                        return false;
                    }
                }
            }
        }
        //No hubo coincidencias, así que hay disponibilidad
        return true;
    }

    @Transactional
    public void eliminarReserva(int id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> buscarTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReservaPorId(int id) {
        return reservaRepository.findById(id);
    }

    @Transactional
    public Reserva modificarReserva(int id, Reserva reservaActualizada) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            reserva.get().setFecha_inicio(reservaActualizada.getFecha_inicio());
            reserva.get().setFecha_fin(reservaActualizada.getFecha_fin());
            return reservaRepository.save(reserva.get());
        }else{
            throw new NoSuchElementException("Reserva no encontrada");
        }
    }

}

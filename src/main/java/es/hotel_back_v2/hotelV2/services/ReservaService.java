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
        Optional<Cliente> cliente = clienteRepository.findByDni(reserva.getCliente().getDni());

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

        //Recorremos todas las reservas existentes
        for (Reserva reserva : todasReservas) {
            //Verificamos si la reserva tiene habitaciones solicitadas
            for (Habitacion habitacion : habitacionesSolicitadas) {
                if (reserva.getHabitaciones().contains(habitacion)) {
                    //Compara las fechas para verificar si hay coincidencias
                    long inicioExistente = reserva.getFecha_inicio().getTime();
                    long finExistente = reserva.getFecha_fin().getTime();
                    long nuevoInicio = fechaInicio.getTime();
                    long nuevoFin = fechaFin.getTime();

                    //Verificamos si hay coincidencias entre las fechas de la nueva reserva y la existente
                    if (nuevoInicio < finExistente && nuevoFin > inicioExistente) {
                        //Si coinciden, devolvemos que la habitación está ocupada
                        System.out.println("Habitación ocupada: " + habitacion.getNumero());
                        return false;
                    }
                }
            }
        }
        //Si no hay coincidencias, hay disponibilidad
        return true;
    }


    @Transactional
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> buscarTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Transactional
    public Reserva modificarReserva(Long id, Reserva reservaActualizada) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            reserva.get().setFecha_inicio(reservaActualizada.getFecha_inicio());
            reserva.get().setFecha_fin(reservaActualizada.getFecha_fin());
            return reservaRepository.save(reserva.get());
        }else{
            throw new NoSuchElementException("Reserva no encontrada");
        }
    }

    //EndPoint para crear factura
    public String generarFactura(Long idReserva) {
        Optional<Reserva> reservaOpt = reservaRepository.findById(idReserva);
        if (reservaOpt.isEmpty()) {
            throw new RuntimeException("Reserva no encontrada");
        }
        Reserva reserva = reservaOpt.get();

        double total = reserva.getHabitaciones().stream().mapToDouble(Habitacion::getPrecio).sum();
        return "Factura de la Reserva:\n" +
                "Cliente: " + reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido() + "\n" +
                "Fecha de Entrada: " + reserva.getFecha_inicio() + "\n" +
                "Fecha de Salida: " + reserva.getFecha_fin() + "\n" +
                "Total a Pagar: $" + total;
    }

}

package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Reserva;
import es.hotel_back_v2.hotelV2.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public void crearReserva(Reserva reserva) {
        reservaRepository.save(reserva);
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

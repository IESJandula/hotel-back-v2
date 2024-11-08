package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.model.Reserva;
import es.hotel_back_v2.hotelV2.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

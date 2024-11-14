package es.hotel_back_v2.hotelV2.controllers;

import es.hotel_back_v2.hotelV2.model.Reserva;
import es.hotel_back_v2.hotelV2.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public Reserva agregarReserva(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }

    @GetMapping("/mostrartodas")
    public List<Reserva> buscarTodasReservas() {
        return reservaService.buscarTodas();
    }

    @GetMapping("/buscarporid/{id}")
    public Optional<Reserva> buscarReservaPorId(@PathVariable int id) {
        return reservaService.buscarReservaPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarReserva(@PathVariable int id) {
        reservaService.eliminarReserva(id);
    }

    @PutMapping("/modificar/{id}")
    public Reserva modificarReserva(@PathVariable int id, @RequestBody Reserva reservaActualizada) {
        return reservaService.modificarReserva(id, reservaActualizada);
    }

    //EndPoint crear factura apartir de una reeserva
    @GetMapping("/generarFactura/{idReserva}")
    public ResponseEntity<String> generarFactura(@PathVariable int idReserva) {
        try {
            String factura = reservaService.generarFactura(idReserva);
            return ResponseEntity.ok(factura);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al generar factura");
        }
    }


}

package es.hotel_back_v2.hotelV2.controllers;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.services.HabitacionService;
import es.hotel_back_v2.hotelV2.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitacion")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ReservaService reservaService;

    //CRUD
    @PostMapping
    public Habitacion agregarHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.crearHabitacion(habitacion);
    }

    @GetMapping("/mostrarTodas")
    public List<Habitacion> buscarTodasHabitaciones() {
        return habitacionService.buscarHabitaciones();

    }

    @GetMapping("/buscarporNumero/{numero}")
    public ResponseEntity<Habitacion> buscarPorNumero(@PathVariable int numero) {
        // Lógica de búsqueda
        return ResponseEntity.ok(habitacionService.buscarPorNumero(numero));
    }

    @DeleteMapping("/eliminarHabitacion/{numero}")
    public void eliminarHabitacion(@PathVariable int numero) {
        habitacionService.eliminarHabitacion(numero);
    }

    //Endpoint para obtener las habitaciones ocupadas en una fecha específica
    @GetMapping("/habitaciones-ocupadas/{fecha}")
    public List<Habitacion> obtenerHabitacionesOcupadas(@PathVariable("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return habitacionService.obtenerHabitacionesOcupadas(fecha);
    }

}

package es.hotel_back_v2.hotelV2.controllers;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitacion")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    public Habitacion agregarHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.crearHabitacion(habitacion);
    }

    @GetMapping("/mostrarTodas")
    public List<Habitacion> buscarTodasHabitaciones() {
        return habitacionService.buscarHabitaciones();

    }
    @GetMapping("/buscarporNumero/{numero}")
    public Optional<Habitacion> buscarHabitacionesPorId(@RequestParam Long numero){
        return habitacionService.buscarHabitacionPorId(numero);
    }

    @DeleteMapping("/eliminarHabitacion/{numero}")
    public void eliminarHabitacion(@PathVariable Long numero) {
        habitacionService.eliminarHabitacion(numero);
    }






}

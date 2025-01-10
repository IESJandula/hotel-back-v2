package es.hotel_back_v2.hotelV2.controllers;

import es.hotel_back_v2.hotelV2.model.Habitacion;
import es.hotel_back_v2.hotelV2.repositories.HabitacionRepository;
import es.hotel_back_v2.hotelV2.services.HabitacionService;
import es.hotel_back_v2.hotelV2.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private HabitacionRepository habitacionRepository;

    //añadir habitación
    @PostMapping
    public ResponseEntity<Habitacion> agregarHabitacion(@RequestBody Habitacion habitacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(habitacionService.crearHabitacion(habitacion));
    }

    //mostrar todas las habitaciones
    @GetMapping("/mostrarTodas")
    public ResponseEntity<List<Habitacion>> buscarTodasHabitaciones() {
        return ResponseEntity.ok(habitacionRepository.findAll());

    }

    //mostrar habitación por número
    @GetMapping("/buscarporNumero/{numero}")
    public ResponseEntity<Optional<Habitacion>> buscarPorNumero(@PathVariable Long numero) {
        return ResponseEntity.ok(habitacionRepository.findById(numero));
    }

    //eliminar habitación por número
    @DeleteMapping("/eliminarHabitacion/{numero}")
    public void eliminarHabitacion(@PathVariable Long numero) {
        habitacionService.eliminarHabitacion(numero);
    }

    //obtener las habitaciones ocupadas en una fecha específica
    @GetMapping("/habitaciones-ocupadas/{fecha}")
    public ResponseEntity<List<Habitacion>> obtenerHabitacionesOcupadas(@PathVariable("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return ResponseEntity.ok(habitacionService.obtenerHabitacionesOcupadas(fecha));
    }

    @PutMapping("/modificar/{numero}")
    public ResponseEntity<Habitacion> modificarHabitacion(@PathVariable Long numero, @RequestBody Habitacion habitacionActualizada) {
        Habitacion habitacionModificada = habitacionService.modificarHabitacion(numero, habitacionActualizada);
        return ResponseEntity.ok(habitacionModificada);
    }

}

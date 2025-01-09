package es.hotel_back_v2.hotelV2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @NotBlank(message = "El tipo de habitación no puede estar vacío")
    private String tipo;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    private double precio;

    @Enumerated(EnumType.STRING)
    private Estado estado;


    @Min(value = 1, message = "La capacidad debe ser al menos 1 persona")
    private int capacidad;

    //relaciones

    @ManyToMany(mappedBy = "habitaciones")
    private List<Reserva> reservas;

    //constructores

    public Habitacion(String tipo, double precio, Estado estado, int capacidad) {
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
        this.capacidad = capacidad;
    }

    public Habitacion() {}

    public enum Estado {
        DISPONIBLE,
        OCUPADA
    }

    //getters and setters

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
package es.hotel_back_v2.hotelV2.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private String tipo;
    private double precio;
    private String estado;
    private int capacidad;

    //Relacion con Reserva
    @ManyToMany(mappedBy = "habitaciones")
    private List<Reserva> reservas;

    public Habitacion(String tipo, double precio, String estado, int capacidad) {
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
        this.capacidad = capacidad;
    }
    public Habitacion() {}

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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

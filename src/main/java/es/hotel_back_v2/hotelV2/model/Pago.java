package es.hotel_back_v2.hotelV2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
public class Pago {

    @Id
    private Long id;

    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que 0")
    private float monto;

    @NotBlank(message = "El método de pago no puede estar vacío")
    private String metodo;

    private String factura;

    @NotNull(message = "La fecha de pago no puede ser nula")
    @PastOrPresent(message = "La fecha de pago no puede ser futura")
    private LocalDate fecha;

    //relaciones

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    //constructores

    public Pago() {
    }

    public Pago(Long id, float monto, String metodo, String factura, LocalDate fecha) {
        this.id = id;
        this.monto = monto;
        this.metodo = metodo;
        this.factura = factura;
        this.fecha = fecha;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}

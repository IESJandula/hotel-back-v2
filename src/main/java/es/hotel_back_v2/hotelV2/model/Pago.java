package es.hotel_back_v2.hotelV2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Pago {

    @Id
    private Long id;
    private float monto;
    private String metodo;
    private String factura;
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

    //Getters and Setters


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

    //getters and setters de las relaciones


    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}

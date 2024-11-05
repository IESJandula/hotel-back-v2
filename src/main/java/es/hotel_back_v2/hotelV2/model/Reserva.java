package es.hotel_back_v2.hotelV2.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Reserva {

    @Id
    private int id;

    private Date fecha_inicio;
    private Date fecha_fin;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Reserva(int id, Date fecha_inicio, Date fecha_fin) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                '}';
    }
}

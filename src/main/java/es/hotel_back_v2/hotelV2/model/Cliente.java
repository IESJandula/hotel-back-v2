package es.hotel_back_v2.hotelV2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id
    private String dni;
}

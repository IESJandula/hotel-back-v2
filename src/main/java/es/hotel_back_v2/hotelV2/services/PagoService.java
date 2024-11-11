package es.hotel_back_v2.hotelV2.services;

import es.hotel_back_v2.hotelV2.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;


}

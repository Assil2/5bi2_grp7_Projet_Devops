package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    private static final Logger logger = LogManager.getLogger(ReservationRestController.class);

    private final IReservationService reservationService;

    // http://localhost:8089/tpfoyer/reservation/retrieve-all-reservations
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        logger.info("Request to retrieve all reservations");
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        logger.debug("Reservations retrieved: {}", listReservations);
        return listReservations;
    }

    // http://localhost:8089/tpfoyer/reservation/retrieve-reservation/8
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String rId) {
        logger.info("Request to retrieve reservation with ID: {}", rId);
        try {
            Reservation reservation = reservationService.retrieveReservation(rId);
            logger.debug("Reservation retrieved: {}", reservation);
            return reservation;
        } catch (Exception e) {
            logger.error("Error retrieving reservation with ID: {}", rId, e);
            throw e;
        }
    }
}

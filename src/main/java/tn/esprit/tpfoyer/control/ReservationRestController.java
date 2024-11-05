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
        Reservation reservation = reservationService.retrieveReservation(rId);
        logger.debug("Reservation retrieved: {}", reservation);
        return reservation;
    }

    // Retrieve reservations by date and status
    @GetMapping("/retrieve-reservation-date-status/{d}/{v}")
    public List<Reservation> retrieveReservationParDateEtStatus(
            @PathVariable("d") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d, 
            @PathVariable("v") boolean b) {
        logger.info("Request to retrieve reservations by date {} and status {}", d, b);
        List<Reservation> reservations = reservationService.trouverResSelonDateEtStatus(d, b);
        logger.debug("Reservations retrieved for date {} and status {}: {}", d, b, reservations);
        return reservations;
    }

    // http://localhost:8089/tpfoyer/reservation/add-reservation
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        logger.info("Request to add a new reservation");
        Reservation reservation = reservationService.addReservation(r);
        logger.debug("New reservation added: {}", reservation);
        return reservation;
    }

    // http://localhost:8089/tpfoyer/reservation/remove-reservation/{reservation-id}
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String rId) {
        logger.info("Request to remove reservation with ID: {}", rId);
        reservationService.removeReservation(rId);
        logger.debug("Reservation with ID {} removed", rId);
    }

    // http://localhost:8089/tpfoyer/reservation/modify-reservation
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        logger.info("Request to modify reservation");
        Reservation reservation = reservationService.modifyReservation(r);
        logger.debug("Reservation modified: {}", reservation);
        return reservation;
    }

}

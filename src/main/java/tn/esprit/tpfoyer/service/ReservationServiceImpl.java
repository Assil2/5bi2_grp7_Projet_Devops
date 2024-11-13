package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;

    public List<Reservation> retrieveAllReservations() {
        logger.info("Starting retrieval of all reservations");
        List<Reservation> reservations = reservationRepository.findAll();
        logger.debug("Reservations retrieved: {}", reservations);
        return reservations;
    }

    public Reservation retrieveReservation(String reservationId) {
        logger.info("Starting retrieval of reservation with ID: {}", reservationId);
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent()) {
            logger.debug("Reservation retrieved: {}", reservation.get());
            return reservation.get();
        } else {
            logger.error("Reservation with ID {} not found", reservationId);
            return null; // ou lance une exception si tu préfères
        }
    }

    public Reservation addReservation(Reservation r) {
        logger.info("Adding a new reservation");
        Reservation savedReservation = reservationRepository.save(r);
        logger.debug("New reservation added: {}", savedReservation);
        return savedReservation;
    }

    public Reservation modifyReservation(Reservation reservation) {
        logger.info("Modifying reservation with ID: {}", reservation.getIdReservation());
        Reservation updatedReservation = reservationRepository.save(reservation);
        logger.debug("Reservation modified: {}", updatedReservation);
        return updatedReservation;
    }

    public List<Reservation> trouverResSelonDateEtStatus(Date d, boolean b) {
        logger.info("Retrieving reservations by date {} and status {}", d, b);
        List<Reservation> reservations = reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(d, b);
        logger.debug("Reservations found for date {} and status {}: {}", d, b, reservations);
        return reservations;
    }

    public void removeReservation(String reservationId) {
        logger.info("Removing reservation with ID: {}", reservationId);
        try {
            reservationRepository.deleteById(reservationId);
            logger.debug("Reservation with ID {} successfully removed", reservationId);
        } catch (Exception e) {
            logger.error("Error removing reservation with ID {}", reservationId, e);
        }
    }
}

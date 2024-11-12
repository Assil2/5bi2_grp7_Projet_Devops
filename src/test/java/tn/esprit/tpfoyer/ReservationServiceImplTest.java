package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        reservation.setIdReservation("123");
        reservation.setEstValide(true);
    }

    @Test
    void testRetrieveAllReservations() {
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.retrieveAllReservations();

        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservation() {
        when(reservationRepository.findById("123")).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.retrieveReservation("123");

        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
        verify(reservationRepository, times(1)).findById("123");
    }

    @Test
    void testAddReservation() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.addReservation(reservation);

        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testModifyReservation() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.modifyReservation(reservation);

        assertNotNull(result);
        assertEquals("123", result.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testFindReservationsByDateAndStatus() {
        Date date = new Date();
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, true))
                .thenReturn(reservations);

        List<Reservation> result = reservationService.trouverResSelonDateEtStatus(date, true);

        assertEquals(1, result.size());
        verify(reservationRepository, times(1))
                .findAllByAnneeUniversitaireBeforeAndEstValide(date, true);
    }

    @Test
    void testRemoveReservation() {
        doNothing().when(reservationRepository).deleteById("123");

        reservationService.removeReservation("123");

        verify(reservationRepository, times(1)).deleteById("123");
    }
}

package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoyerServiceImplJUnitTest {

    FoyerServiceImpl foyerService;
    FoyerRepository foyerRepository;

    @BeforeEach
    public void setUp() {
        foyerRepository = mock(FoyerRepository.class);
        foyerService = new FoyerServiceImpl(foyerRepository);
    }

    @Test
    public void testRetrieveAllFoyers() {
        List<Foyer> foyers = List.of(new Foyer());
        when(foyerRepository.findAll()).thenReturn(foyers);

        List<Foyer> result = foyerService.retrieveAllFoyers();

        assertEquals(foyers.size(), result.size());
    }

    @Test
    public void testRetrieveFoyer() {
        Foyer foyer = new Foyer();
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.retrieveFoyer(1L);

        assertEquals(foyer, result);
    }

    @Test
    public void testAddFoyer() {
        Foyer foyer = new Foyer();
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.addFoyer(foyer);

        assertEquals(foyer, result);
    }

    @Test
    public void testModifyFoyer() {
        Foyer foyer = new Foyer();
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.modifyFoyer(foyer);

        assertEquals(foyer, result);
    }

    @Test
    public void testRemoveFoyer() {
        doNothing().when(foyerRepository).deleteById(1L);

        foyerService.removeFoyer(1L);

        verify(foyerRepository).deleteById(1L);
    }
}
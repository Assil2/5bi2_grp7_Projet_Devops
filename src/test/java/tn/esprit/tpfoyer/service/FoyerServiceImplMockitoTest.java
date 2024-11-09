package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FoyerServiceImplMockitoTest {

    @InjectMocks
    FoyerServiceImpl foyerService;

    @Mock
    FoyerRepository foyerRepository;

    public FoyerServiceImplMockitoTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllFoyers() {
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(new Foyer());

        when(foyerRepository.findAll()).thenReturn(foyers);

        List<Foyer> result = foyerService.retrieveAllFoyers();

        assertEquals(foyers.size(), result.size());
        verify(foyerRepository).findAll();
    }

    @Test
    public void testRetrieveFoyer() {
        Foyer foyer = new Foyer();
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.retrieveFoyer(1L);

        assertNotNull(result);
        assertEquals(foyer, result);
        verify(foyerRepository).findById(1L);
    }

    @Test
    public void testAddFoyer() {
        Foyer foyer = new Foyer();
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.addFoyer(foyer);

        assertNotNull(result);
        assertEquals(foyer, result);
        verify(foyerRepository).save(foyer);
    }

    @Test
    public void testModifyFoyer() {
        Foyer foyer = new Foyer();
        when(foyerRepository.save(foyer)).thenReturn(foyer);

        Foyer result = foyerService.modifyFoyer(foyer);

        assertNotNull(result);
        assertEquals(foyer, result);
        verify(foyerRepository).save(foyer);
    }

    @Test
    public void testRemoveFoyer() {
        doNothing().when(foyerRepository).deleteById(1L);

        foyerService.removeFoyer(1L);

        verify(foyerRepository).deleteById(1L);
    }
}
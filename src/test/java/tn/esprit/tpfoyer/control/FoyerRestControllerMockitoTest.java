package tn.esprit.tpfoyer.control;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.IFoyerService;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FoyerRestControllerMockitoTest {

    @InjectMocks
    FoyerRestController foyerRestController;

    @Mock
    IFoyerService foyerService;

    public FoyerRestControllerMockitoTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFoyers() {
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(new Foyer());

        when(foyerService.retrieveAllFoyers()).thenReturn(foyers);

        List<Foyer> result = foyerRestController.getFoyers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(foyerService).retrieveAllFoyers();
    }

    @Test
    public void testRetrieveFoyer() {
        Foyer foyer = new Foyer();
        when(foyerService.retrieveFoyer(anyLong())).thenReturn(foyer);

        Foyer result = foyerRestController.retrieveFoyer(1L);

        assertNotNull(result);
        verify(foyerService).retrieveFoyer(1L);
    }

    @Test
    public void testAddFoyer() {
        Foyer foyer = new Foyer();
        when(foyerService.addFoyer(foyer)).thenReturn(foyer);

        Foyer result = foyerRestController.addFoyer(foyer);

        assertNotNull(result);
        verify(foyerService).addFoyer(foyer);
    }

    @Test
    public void testRemoveFoyer() {
        doNothing().when(foyerService).removeFoyer(anyLong());

        foyerRestController.removeFoyer(1L);

        verify(foyerService).removeFoyer(1L);
    }

    @Test
    public void testModifyFoyer() {
        Foyer foyer = new Foyer();
        when(foyerService.modifyFoyer(foyer)).thenReturn(foyer);

        Foyer result = foyerRestController.modifyFoyer(foyer);

        assertNotNull(result);
        verify(foyerService).modifyFoyer(foyer);
    }
}
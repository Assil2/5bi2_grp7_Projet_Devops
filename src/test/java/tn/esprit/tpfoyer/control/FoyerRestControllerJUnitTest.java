package tn.esprit.tpfoyer.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.IFoyerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FoyerRestControllerJUnitTest {

    FoyerRestController foyerRestController;
    IFoyerService foyerService;

    @BeforeEach
    public void setUp() {
        foyerService = mock(IFoyerService.class);
        foyerRestController = new FoyerRestController(foyerService);
    }

    @Test
    public void testGetFoyers() {
        List<Foyer> foyers = List.of(new Foyer());
        when(foyerService.retrieveAllFoyers()).thenReturn(foyers);

        List<Foyer> result = foyerRestController.getFoyers();

        assertEquals(foyers.size(), result.size());
    }

    @Test
    public void testRetrieveFoyer() {
        Foyer foyer = new Foyer();
        when(foyerService.retrieveFoyer(1L)).thenReturn(foyer);

        Foyer result = foyerRestController.retrieveFoyer(1L);

        assertEquals(foyer, result);
    }

    @Test
    public void testAddFoyer() {
        Foyer foyer = new Foyer();
        when(foyerService.addFoyer(foyer)).thenReturn(foyer);

        Foyer result = foyerRestController.addFoyer(foyer);

        assertEquals(foyer, result);
    }

    @Test
    public void testRemoveFoyer() {
        foyerRestController.removeFoyer(1L);
        // No return value to assert; just verifying method runs without errors
    }

    @Test
    public void testModifyFoyer() {
        Foyer foyer = new Foyer();
        when(foyerService.modifyFoyer(foyer)).thenReturn(foyer);

        Foyer result = foyerRestController.modifyFoyer(foyer);

        assertEquals(foyer, result);
    }
}
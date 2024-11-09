package tn.esprit.tpfoyer;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)

public class blockServiceTest {
    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    private final Bloc bloc = new Bloc(1L, "Bloc A", 50, null, new HashSet<>());

    @Test
    void testRetrieveAllBlocs() {
        List<Bloc> blocs = Arrays.asList(bloc, new Bloc(2L, "Bloc B", 30, null, new HashSet<>()));
        Mockito.when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> retrievedBlocs = blocService.retrieveAllBlocs();

        Assertions.assertNotNull(retrievedBlocs);
        Assertions.assertEquals(2, retrievedBlocs.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveBlocsSelonCapacite() {
        List<Bloc> blocs = Arrays.asList(bloc, new Bloc(2L, "Bloc B", 60, null, new HashSet<>()));
        Mockito.when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> result = blocService.retrieveBlocsSelonCapacite(50);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveBloc() {
        Long blocId = 1L;
        Mockito.when(blocRepository.findById(blocId)).thenReturn(Optional.of(bloc));

        Bloc retrievedBloc = blocService.retrieveBloc(blocId);

        Assertions.assertNotNull(retrievedBloc);
        Assertions.assertEquals(blocId, retrievedBloc.getIdBloc());
        verify(blocRepository, times(1)).findById(blocId);
    }

    @Test
    void testAddBloc() {
        Mockito.when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc addedBloc = blocService.addBloc(bloc);

        Assertions.assertNotNull(addedBloc);
        Assertions.assertEquals("Bloc A", addedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testModifyBloc() {
        Mockito.when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc updatedBloc = blocService.modifyBloc(bloc);

        Assertions.assertNotNull(updatedBloc);
        Assertions.assertEquals("Bloc A", updatedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testRemoveBloc() {
        Long blocId = 1L;

        blocService.removeBloc(blocId);

        verify(blocRepository, times(1)).deleteById(blocId);
    }

    @Test
    void testTrouverBlocsSansFoyer() {
        List<Bloc> blocsSansFoyer = Arrays.asList(bloc);
        Mockito.when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocsSansFoyer);

        List<Bloc> result = blocService.trouverBlocsSansFoyer();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }

    @Test
    void testTrouverBlocsParNomEtCap() {
        String nomBloc = "Bloc A";
        long capacite = 50;
        List<Bloc> blocs = Arrays.asList(bloc);
        Mockito.when(blocRepository.findAllByNomBlocAndCapaciteBloc(nomBloc, capacite)).thenReturn(blocs);

        List<Bloc> result = blocService.trouverBlocsParNomEtCap(nomBloc, capacite);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc(nomBloc, capacite);
    }
}

package tn.esprit.tpfoyer.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ChambreRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IChambreService chambreService;

    @InjectMocks
    private ChambreRestController chambreRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(chambreRestController).build();
    }

    @Test
    void testGetChambres() throws Exception {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE);

        List<Chambre> chambres = Collections.singletonList(chambre);
        when(chambreService.retrieveAllChambres()).thenReturn(chambres);

        mockMvc.perform(get("/chambre/retrieve-all-chambres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idChambre").value(1L))
                .andExpect(jsonPath("$[0].numeroChambre").value(101))
                .andExpect(jsonPath("$[0].typeC").value("SIMPLE"));

        verify(chambreService, times(1)).retrieveAllChambres();
    }

    @Test
    void testRetrieveChambre() throws Exception {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(2L);
        chambre.setNumeroChambre(202);
        chambre.setTypeC(TypeChambre.DOUBLE);

        when(chambreService.retrieveChambre(2L)).thenReturn(chambre);

        mockMvc.perform(get("/chambre/retrieve-chambre/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idChambre").value(2L))
                .andExpect(jsonPath("$.numeroChambre").value(202))
                .andExpect(jsonPath("$.typeC").value("DOUBLE"));

        verify(chambreService, times(1)).retrieveChambre(2L);
    }

    @Test
    void testAddChambre() throws Exception {
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(303);
        chambre.setTypeC(TypeChambre.SIMPLE);

        when(chambreService.addChambre(any(Chambre.class))).thenReturn(chambre);

        mockMvc.perform(post("/chambre/add-chambre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numeroChambre\": 303, \"typeC\": \"SIMPLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroChambre").value(303))
                .andExpect(jsonPath("$.typeC").value("SIMPLE"));

        verify(chambreService, times(1)).addChambre(any(Chambre.class));
    }

    @Test
    void testRemoveChambre() throws Exception {
        doNothing().when(chambreService).removeChambre(3L);

        mockMvc.perform(delete("/chambre/remove-chambre/3"))
                .andExpect(status().isOk());

        verify(chambreService, times(1)).removeChambre(3L);
    }

    @Test
    void testModifyChambre() throws Exception {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(4L);
        chambre.setNumeroChambre(404);
        chambre.setTypeC(TypeChambre.SIMPLE);

        when(chambreService.modifyChambre(any(Chambre.class))).thenReturn(chambre);

        mockMvc.perform(put("/chambre/modify-chambre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idChambre\": 4, \"numeroChambre\": 404, \"typeC\": \"SIMPLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idChambre").value(4L))
                .andExpect(jsonPath("$.numeroChambre").value(404))
                .andExpect(jsonPath("$.typeC").value("SIMPLE"));

        verify(chambreService, times(1)).modifyChambre(any(Chambre.class));
    }

    @Test
    void testFindChambreByType() throws Exception {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(5L);
        chambre.setNumeroChambre(505);
        chambre.setTypeC(TypeChambre.TRIPLE);

        when(chambreService.recupererChambresSelonTyp(TypeChambre.TRIPLE))
                .thenReturn(Collections.singletonList(chambre));

        mockMvc.perform(get("/chambre/trouver-chambres-selon-typ/TRIPLE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idChambre").value(5L))
                .andExpect(jsonPath("$[0].numeroChambre").value(505))
                .andExpect(jsonPath("$[0].typeC").value("TRIPLE"));

        verify(chambreService, times(1)).recupererChambresSelonTyp(TypeChambre.TRIPLE);
    }

    @Test
    void testFindChambreByEtudiant() throws Exception {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(6L);
        chambre.setNumeroChambre(606);
        chambre.setTypeC(TypeChambre.SIMPLE);

        when(chambreService.trouverchambreSelonEtudiant(12345L)).thenReturn(chambre);

        mockMvc.perform(get("/chambre/trouver-chambre-selon-etudiant/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idChambre").value(6L))
                .andExpect(jsonPath("$.numeroChambre").value(606))
                .andExpect(jsonPath("$.typeC").value("SIMPLE"));

        verify(chambreService, times(1)).trouverchambreSelonEtudiant(12345L);
    }
}

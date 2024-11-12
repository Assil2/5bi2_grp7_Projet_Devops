package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer.control.BlocRestController;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BlocRestControllerTest {


    private MockMvc mockMvc;

    @Mock
    private IBlocService blocService;

    @InjectMocks
    private BlocRestController blocRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blocRestController).build();
    }

    @Test
    public void testGetBlocs() throws Exception {
        Bloc bloc1 = new Bloc(1L, "Bloc A", 100, null, Collections.emptySet());
        Bloc bloc2 = new Bloc(2L, "Bloc B", 200, null, Collections.emptySet());
        List<Bloc> blocs = Arrays.asList(bloc1, bloc2);

        when(blocService.retrieveAllBlocs()).thenReturn(blocs);

        mockMvc.perform(get("/bloc/retrieve-all-blocs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomBloc").value("Bloc A"))
                .andExpect(jsonPath("$[1].capaciteBloc").value(200));

        verify(blocService, times(1)).retrieveAllBlocs();
    }

    @Test
    public void testRetrieveBloc() throws Exception {
        Bloc bloc = new Bloc(1L, "Bloc A", 100, null, Collections.emptySet());

        when(blocService.retrieveBloc(1L)).thenReturn(bloc);

        mockMvc.perform(get("/bloc/retrieve-bloc/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc A"));

        verify(blocService, times(1)).retrieveBloc(1L);
    }

    @Test
    public void testAddBloc() throws Exception {
        Bloc bloc = new Bloc(1L, "Bloc C", 150, null, Collections.emptySet());

        when(blocService.addBloc(any(Bloc.class))).thenReturn(bloc);

        mockMvc.perform(post("/bloc/add-bloc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomBloc\": \"Bloc C\", \"capaciteBloc\": 150}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc C"));

        verify(blocService, times(1)).addBloc(any(Bloc.class));
    }

    @Test
    public void testRemoveBloc() throws Exception {
        doNothing().when(blocService).removeBloc(1L);

        mockMvc.perform(delete("/bloc/remove-bloc/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(blocService, times(1)).removeBloc(1L);
    }

    @Test
    public void testModifyBloc() throws Exception {
        Bloc updatedBloc = new Bloc(1L, "Bloc D", 180, null, Collections.emptySet());

        when(blocService.modifyBloc(any(Bloc.class))).thenReturn(updatedBloc);

        mockMvc.perform(put("/bloc/modify-bloc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idBloc\": 1, \"nomBloc\": \"Bloc D\", \"capaciteBloc\": 180}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomBloc").value("Bloc D"));

        verify(blocService, times(1)).modifyBloc(any(Bloc.class));
    }
}

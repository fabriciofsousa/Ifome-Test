package br.com.ada.ifome.veiculo;

import br.com.ada.ifome.controller.VeiculoController;
import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Calendar;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class VeiculoControllerTest {
    @InjectMocks
    private VeiculoController veiculoController;
    @Mock
    private VeiculoService veiculoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
    }

    @Test
    public void cadastrarVeiculo() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setDataModelo(Calendar.getInstance());
        veiculo.setPlaca("ZZZ9999");
        veiculo.setRenavam("37759184770");

        Mockito.when(veiculoService.salvar(any())).thenReturn(veiculo);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(veiculo)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

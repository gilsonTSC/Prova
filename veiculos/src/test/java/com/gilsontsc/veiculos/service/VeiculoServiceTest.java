package com.gilsontsc.veiculos.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilsontsc.veiculos.api.dto.VeiculoDTO;
import com.gilsontsc.veiculos.api.entity.Veiculo;
import com.gilsontsc.veiculos.api.services.VeiculoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VeiculoServiceTest {

	private static final Long ID = 1L;
	private static final String VEICULO = "Gol";
	private static final String MARCA = "volkswagen";
	private static final Integer ANO = 2010;
	private static final String DESCRICAO = "Seminovo";
	private static final Boolean VENDIDO = false;
	private static final Date CREATED = new Date();
	private static final Date UPDATED = new Date();
	private static final String URI = "/veiculos";
	
	@MockBean
	VeiculoService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception {
		
		BDDMockito.given(this.service.salvar(Mockito.any(Veiculo.class))).willReturn(getMockVeiculo());
		
		mvc.perform(MockMvcRequestBuilders.post(URI).content(this.getJsonPayLoad(ID, VEICULO, MARCA, ANO, DESCRICAO, VENDIDO, CREATED, UPDATED))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.veiculo").value(VEICULO))
		.andExpect(jsonPath("$.data.marca").value(MARCA))
		.andExpect(jsonPath("$.data.ano").value(ANO))
		.andExpect(jsonPath("$.data.descricao").value(DESCRICAO))
		.andExpect(jsonPath("$.data.vendido").value(VENDIDO))
		.andExpect(jsonPath("$.data.created").value(CREATED));
	}
	
	@Test
	public void testSaveInvalidVeiculo() throws JsonProcessingException, Exception {
		mvc.perform(MockMvcRequestBuilders.post(URI).content(this.getJsonPayLoad(ID, null, MARCA, ANO, DESCRICAO, VENDIDO, CREATED, UPDATED))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}

	
	public Veiculo getMockVeiculo() {
		return Veiculo.builder()
				  .id(ID)
				  .veiculo(VEICULO)
				  .marca(MARCA)
				  .ano(ANO)
				  .descricao(DESCRICAO)
				  .vendido(VENDIDO)
				  .created(CREATED)
				  .updated(UPDATED)
				  .build();
	}
	
	public String getJsonPayLoad(Long id, String veiculo, String marca, Integer ano, String descricao, Boolean vendido, Date created, Date updated) throws JsonProcessingException {
		VeiculoDTO dto = VeiculoDTO.builder()
								   .id(id)
								   .veiculo(veiculo)
								   .marca(marca)
								   .ano(ano)
								   .descricao(descricao)
								   .vendido(vendido)
								   .created(created)
								   .updated(updated)
								   .build();
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
	
}
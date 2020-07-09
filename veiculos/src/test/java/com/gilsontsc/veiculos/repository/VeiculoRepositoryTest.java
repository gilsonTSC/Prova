package com.gilsontsc.veiculos.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.gilsontsc.veiculos.api.entity.Veiculo;
import com.gilsontsc.veiculos.api.repository.VeiculoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class VeiculoRepositoryTest {

	private static final Long ID = 1L;
	private static final String VEICULO = "Gol";
	private static final String MARCA = "volkswagen";
	private static final Integer ANO = 2010;
	private static final String DESCRICAO = "Seminovo";
	private static final Boolean VENDIDO = true;
	private static final Date CREATED = new Date();
	private static final Date UPDATED = new Date();
	
	@Autowired
	VeiculoRepository repository;
	
	@Before
	public void setUp() {
		Veiculo v = this.getVeiculo();
		
		this.repository.save(v);
	}
	
	@After
	public void tearDown() {
		this.repository.deleteAll();
	}
	
	@Test
	public void testSave() {
		Veiculo response = this.repository.save(this.getVeiculo());
		
		assertNotNull(response);
	}
	
	@Test
	public void testFindById() {
		Veiculo v = this.repository.save(this.getVeiculo());
		
		Optional<Veiculo> response = this.repository.findById(v.getId());
		assertTrue(response.isPresent());
	}
	
	@Test
	public void testFindAll() {
		this.repository.save(this.getVeiculo());
		List<Veiculo> response = this.repository.findAll();

		assertTrue(response.size() == 1);
	}
	
	@Test
	public void testDelete() {
		Veiculo v = this.repository.save(this.getVeiculo());
		
		this.repository.deleteById(v.getId());
		Optional<Veiculo> response = this.repository.findById(v.getId());
		assertFalse(response.isPresent());
	}
	
	public Veiculo getVeiculo() {
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
	
}
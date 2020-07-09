package com.gilsontsc.veiculos.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilsontsc.veiculos.api.dto.VeiculoDTO;
import com.gilsontsc.veiculos.api.entity.Veiculo;
import com.gilsontsc.veiculos.api.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repo;
	
	public Veiculo salvar(Veiculo Veiculo) {
		return this.repo.save(Veiculo);
	}
	
	public Optional<Veiculo> buscarPorId(Long id){
		return this.repo.findById(id);
	}
	
	public List<Veiculo> buscarTodos(){
		return this.repo.findAll();
	}
	
	public void deletar(Long id){
		this.repo.deleteById(id);
	}
	
	public List<Veiculo> buscarPor(Veiculo veiculo){
		return this.repo.find(veiculo.getVeiculo(), veiculo.getMarca(), veiculo.getAno(), veiculo.getDescricao());
	}
	
	public Veiculo convertDtoToEntity(VeiculoDTO dto) {
		return Veiculo.builder()
					  .id(dto.getId())
					  .veiculo(dto.getVeiculo())
					  .marca(dto.getMarca())
					  .ano(dto.getAno())
					  .descricao(dto.getDescricao())
					  .vendido(dto.getVendido())
					  .created(dto.getCreated())
					  .updated(dto.getUpdated())
					  .build();
	}
	
	public VeiculoDTO convertEntityToDto(Veiculo veiculo) {
		return VeiculoDTO.builder()
						 .id(veiculo.getId())
						 .veiculo(veiculo.getVeiculo())
						 .marca(veiculo.getMarca())
						 .ano(veiculo.getAno())
						 .descricao(veiculo.getDescricao())
						 .vendido(veiculo.getVendido())
						 .created(veiculo.getCreated())
						 .updated(veiculo.getUpdated())
						 .build();
	}
	
}
package com.gilsontsc.veiculos.api.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gilsontsc.veiculos.api.dto.VeiculoDTO;
import com.gilsontsc.veiculos.api.entity.Veiculo;
import com.gilsontsc.veiculos.api.services.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VeiculoDTO save(@RequestBody @Valid VeiculoDTO veiculo) {
		Veiculo v = this.service.convertDtoToEntity(veiculo);
		v.setCreated(new Date());
		v = this.service.salvar(v);
		return this.service.convertEntityToDto(v);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid VeiculoDTO veiculoAtualizado) {
		this.service.buscarPorId(id)
			.map( veiculo -> {
				veiculoAtualizado.setUpdated(new Date());
				this.service.salvar(this.service.convertDtoToEntity(veiculoAtualizado));
				return Void.TYPE;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.service.buscarPorId(id)
			.map( veiculo -> {
				this.service.deletar(veiculo.getId());
				return Void.TYPE;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
	}
	
	@GetMapping
	public List<VeiculoDTO> findAll(){
		List<VeiculoDTO> list = new ArrayList<>();
		this.service.buscarTodos()
			.forEach(veiculo -> {
				list.add(this.service.convertEntityToDto(veiculo));
			});
		return list;
	}
	
	@GetMapping("vendidos")
	public Integer findQtdVendido() {
		List<VeiculoDTO> list = new ArrayList<>();
		this.findAll().forEach(veiculo -> {
			if(veiculo.getVendido()) {
				list.add(veiculo);
			}
		});
		return list.size();
	}
	
	@GetMapping("find")
	public List<VeiculoDTO> findGeneric(@RequestBody VeiculoDTO veiculoDTO) {
		List<VeiculoDTO> list = new ArrayList<>();
		this.service.buscarPor(this.service.convertDtoToEntity(veiculoDTO))
			.forEach(veiculo -> {
				list.add(this.service.convertEntityToDto(veiculo));
			});
		return list;
	}
	
	@GetMapping("{id}")
	public VeiculoDTO findById(@PathVariable Long id) {
		return this.service.buscarPorId(id)
				   .map(veiculo -> {
					   return this.service.convertEntityToDto(veiculo);
				   })
				   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
	}
	
	
	
}
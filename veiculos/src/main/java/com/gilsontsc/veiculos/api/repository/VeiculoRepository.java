package com.gilsontsc.veiculos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gilsontsc.veiculos.api.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

	@Query(" select v from Veiculo v"
            + " where upper( v.veiculo ) like upper( :veiculo ) "
            + "or upper( v.marca ) like upper( :marca ) "
            + "or upper( v.ano ) like upper( :ano ) "
            + "or upper( v.descricao ) like upper( :descricao ) ")
	List<Veiculo> find(
			@Param("veiculo") String veiculo, 
			@Param("marca") String marca, 
			@Param("ano") Integer ano, 
			@Param("descricao") String descricao);
	
	
	
	//List<Veiculo> findByVeiculoIgnoreCaseContainingAndMarcaIgnoreCaseContainingAndAnoIgnoreCaseContainingAndDescricaoIgnoreCaseContainingAndOrderByCreateddesc(String veiculo, String marca, Integer ano, String descricao);

}
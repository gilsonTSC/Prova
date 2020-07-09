package com.gilsontsc.veiculos.api.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VeiculoDTO {

	private Long id;
	
	@NotNull(message = "Informe o Veiculo")
	private String veiculo;
	
	@NotNull(message = "Informe a Marca")
	private String marca;
	
	@NotNull(message = "Informe o Ano")
	private Integer ano;
	
	@NotNull(message = "Informe a Descrição")
	private String descricao;
	
	private Boolean vendido;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date created;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date updated;
	
}
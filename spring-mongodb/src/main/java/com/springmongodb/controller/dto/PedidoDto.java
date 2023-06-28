package com.springmongodb.controller.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoDto {
		
	private UUID uuid;
	
	@NotNull
	private Integer numpedcomp;
	
	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date dtPedido;
	
	@NotNull
	private BigDecimal vlTotal;
	
	private String descricao;
	
	private String urlNfeXml;

}

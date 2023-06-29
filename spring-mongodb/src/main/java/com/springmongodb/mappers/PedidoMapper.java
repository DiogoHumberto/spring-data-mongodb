package com.springmongodb.mappers;

import com.springmongodb.controller.dto.PedidoDto;
import com.springmongodb.model.PedidoModel;

public class PedidoMapper {
	
	public static PedidoModel toEntity(PedidoDto dto) {
		
		return PedidoModel.builder()
				.numpedcomp(dto.getNumpedcomp())
				.dtPedido(dto.getDtPedido())
				.vlTotal(dto.getVlTotal())
				.descricao(dto.getDescricao())
				.build();		
	}
	
	public static PedidoDto toDto(PedidoModel entity, String urlNfeXml) {
		
		return PedidoDto.builder()
				.uuid(entity.getUuid())
				.numpedcomp(entity.getNumpedcomp())
				.dtPedido(entity.getDtPedido())
				.vlTotal(entity.getVlTotal())
				.descricao(entity.getDescricao())
				.urlNfeXml(urlNfeXml).build();
		
	}

}

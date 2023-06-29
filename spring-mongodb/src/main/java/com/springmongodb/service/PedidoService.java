package com.springmongodb.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springmongodb.controller.dto.PedidoDto;
import com.springmongodb.exception.BadRequestException;
import com.springmongodb.mappers.PedidoMapper;
import com.springmongodb.model.DocumentNfeXml;
import com.springmongodb.model.PedidoModel;
import com.springmongodb.repository.DocumentNfeXmlRepository;
import com.springmongodb.repository.PedidoRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;

	private final DocumentNfeXmlRepository documentNfeRepository;

	public PedidoDto criar(PedidoDto dto, MultipartFile file) throws IOException {

		if (pedidoRepository.existsByNumpedcomp(dto.getNumpedcomp())) {

			throw new BadRequestException("numpedcomp já existe!");
		}

		var nfeXml = new DocumentNfeXml(dto.getNumpedcomp(), file.getOriginalFilename(),
				new Binary(BsonBinarySubType.BINARY, file.getBytes()));

		var urlXmldownload = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getNumpedcomp()).toUri().toString();

		return PedidoMapper.toDto(save(PedidoMapper.toEntity(dto), nfeXml), urlXmldownload);

	}

	private PedidoModel save(PedidoModel pedido, DocumentNfeXml nfe) {

		pedidoRepository.findByNumpedcomp(pedido.getNumpedcomp()).ifPresentOrElse((v) -> {
			pedido.setUuid(v.getUuid());
		}, () ->{});

		documentNfeRepository.save(nfe);
		return pedidoRepository.save(pedido);

	}
	
	public DocumentNfeXml downloadFileNfe(@NotNull Integer numpedcomp) {

		return documentNfeRepository.findById(numpedcomp)
				.orElseThrow(() -> new BadRequestException("nfe não encontrada ou inexistente!"));

	}
	
	public PedidoDto buscar(@NotNull Integer numpedcomp) {

		var pedido = pedidoRepository.findByNumpedcomp(numpedcomp)
				.orElseThrow(() -> new BadRequestException("'numpedcomp' não encontrado ou inexistente!"));
		
		var urlXmldownload = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(numpedcomp)
				.toString();
		
		return PedidoMapper.toDto(pedido, urlXmldownload);
	}

}

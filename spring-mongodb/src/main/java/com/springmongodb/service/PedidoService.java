package com.springmongodb.service;

import java.io.IOException;
import java.net.InetAddress;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springmongodb.controller.dto.PedidoDto;
import com.springmongodb.model.DocumentNfeXml;
import com.springmongodb.model.PedidoModel;
import com.springmongodb.repository.DocumentNfeXmlRepository;
import com.springmongodb.repository.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final Environment environment;

	private final PedidoRepository pedidoRepository;

	private final DocumentNfeXmlRepository documentNfeRepository;

	public PedidoDto criar(PedidoDto dto, MultipartFile file) throws IOException {

		var nfe = new DocumentNfeXml(dto.getNumpedcomp(), file.getOriginalFilename(),
				new Binary(BsonBinarySubType.BINARY, file.getBytes()));

		var pedidoModel = PedidoModel.builder().numpedcomp(dto.getNumpedcomp()).dtPedido(dto.getDtPedido())
				.vlTotal(dto.getVlTotal()).descricao(dto.getDescricao()).build();

		documentNfeRepository.save(nfe);

		var pedido = pedidoRepository.save(pedidoModel);

		var urlXmlDownload = InetAddress.getLocalHost().getHostAddress() + ":" + environment.getProperty("server.port");

		return PedidoDto.builder().uuid(pedido.getUuid()).numpedcomp(pedido.getNumpedcomp())
				.dtPedido(pedido.getDtPedido()).vlTotal(pedido.getVlTotal()).descricao(pedido.getDescricao())
				.urlNfeXml(urlXmlDownload).build();

	}

}

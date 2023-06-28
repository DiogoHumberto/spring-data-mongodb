package com.springmongodb.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springmongodb.controller.dto.PedidoDto;
import com.springmongodb.service.PedidoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService pedidoService;

	@PostMapping( consumes = "multipart/form-data")
	public ResponseEntity<PedidoDto> criar(@RequestPart("pedido") @Valid PedidoDto dto,
			@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {

		var pedidoVo = pedidoService.criar(dto, file);

		var uriCreate = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedidoVo.getNumpedcomp())
				.toUri();

		//URI uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(respDto.getId()).toUri();

		return ResponseEntity.created(uriCreate).body(pedidoVo);

	}

}

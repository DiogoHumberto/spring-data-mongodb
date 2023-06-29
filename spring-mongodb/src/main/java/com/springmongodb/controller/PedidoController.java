package com.springmongodb.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

		return ResponseEntity.created(uriCreate).body(pedidoVo);

	}
	
	@GetMapping("/{numpedcomp}")
	public ResponseEntity<String> downloadNfe(@PathVariable @NotNull Integer numpedcomp){
		
		var documentNfe = pedidoService.downloadFileNfe(numpedcomp);
		
		String outResource = Base64.getEncoder().encodeToString(documentNfe.getFileXml().getData());
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + documentNfe.getFileName())
				.body(outResource);
	}
	
	@GetMapping()
	public ResponseEntity<PedidoDto> buscar(@RequestParam( value = "numpedcomp", required = true) Integer numpedcomp){
				
		return ResponseEntity.ok(pedidoService.buscar(numpedcomp));
	}
	

}

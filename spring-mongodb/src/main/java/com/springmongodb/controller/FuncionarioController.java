package com.springmongodb.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springmongodb.model.DocumentFuncionarioModel;
import com.springmongodb.service.FuncionarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
	
	private final FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<List<DocumentFuncionarioModel>> listar() {
		
		return ResponseEntity.ok(funcionarioService.listar());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DocumentFuncionarioModel> obterPorId(@PathVariable String id) {
		
		return ResponseEntity.ok(funcionarioService.obterPorId(id));
		
	}
	
	@PostMapping
	public ResponseEntity<DocumentFuncionarioModel> criar(@RequestBody DocumentFuncionarioModel funcionario, UriComponentsBuilder uriBuilder) {
		
		var respDto = funcionarioService.criar(funcionario);
		
		URI uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(respDto.getId()).toUri();
				
		return ResponseEntity.created(uri).body(respDto);
		
	}
	
}

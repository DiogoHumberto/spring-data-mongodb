package com.springmongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmongodb.model.DocumentFuncionarioModel;
import com.springmongodb.repository.DocumentFuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

	private final DocumentFuncionarioRepository funcionarioRepository;

	public List<DocumentFuncionarioModel> listar() {

		return this.funcionarioRepository.findAll();

	}

	public DocumentFuncionarioModel obterPorId(String id) {

		return this.funcionarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("código não existe!"));
	}

	public DocumentFuncionarioModel criar(DocumentFuncionarioModel funcionario) {
		
		if(funcionario.getChefe() != null && !funcionario.getChefe().getId().isBlank()) {
			
			funcionario.setChefe(this.funcionarioRepository.findById(funcionario.getChefe().getId())
					.orElseThrow(() -> new IllegalArgumentException("Chefe inexistente!")));
		}

		return this.funcionarioRepository.save(funcionario);

	}

}

package com.springmongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmongodb.model.FuncionarioModel;
import com.springmongodb.repository.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

	private final FuncionarioRepository funcionarioRepository;

	public List<FuncionarioModel> listar() {

		return this.funcionarioRepository.findAll();

	}

	public FuncionarioModel obterPorId(String id) {

		return this.funcionarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("código não existe!"));
	}

	public FuncionarioModel criar(FuncionarioModel funcionario) {
		
		if(funcionario.getChefe() != null && !funcionario.getChefe().getId().isBlank()) {
			
			funcionario.setChefe(this.funcionarioRepository.findById(funcionario.getChefe().getId())
					.orElseThrow(() -> new IllegalArgumentException("Chefe inexistente!")));
		}

		return this.funcionarioRepository.save(funcionario);

	}

}

package com.springmongodb.repository;

import com.springmongodb.model.FuncionarioModel;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface  FuncionarioRepository extends MongoRepository <FuncionarioModel, String> {
	

}

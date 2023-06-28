package com.springmongodb.repository;

import com.springmongodb.model.DocumentFuncionarioModel;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface  DocumentFuncionarioRepository extends MongoRepository <DocumentFuncionarioModel, String> {
	

}

package com.springmongodb.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data 
@Document
public class FuncionarioModel {
	
	@Id
	private String id;
	
	private String nome;
	
	private Integer idade;
	
	private BigDecimal salario;
	
	@DBRef
	private FuncionarioModel chefe;

}

package com.springmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data 
@Document
public class DocumentNfeXml {
	
	@Id
	private Integer numpedcomp;
	
	
	

}

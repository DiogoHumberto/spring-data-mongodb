package com.springmongodb.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data 
@Document(collection = "nfeXml")
public class DocumentNfeXml {
	
	@Id
	private Integer numpedcomp;
	
	private String fileName;
	
	private Binary fileXml;
	
	public DocumentNfeXml(Integer numpedcomp, String fileName, Binary fileXml) {
		this.numpedcomp = numpedcomp;
		this.fileName = fileName;
		this.fileXml = fileXml;
	}

}

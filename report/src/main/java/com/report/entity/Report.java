package com.report.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "report")
public class Report {
    
	@Id
	private String id;
	private double monto_diario;
	private double cuentaC_comision;
	
	
}

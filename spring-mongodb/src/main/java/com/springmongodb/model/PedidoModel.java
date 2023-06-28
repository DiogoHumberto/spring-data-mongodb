package com.springmongodb.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido", uniqueConstraints = @UniqueConstraint(columnNames = "numpedcomp"))
public class PedidoModel {
	
	@Id
	@Column(name = "uuid")
	@GeneratedValue
    @UuidGenerator(style = Style.TIME)
	private UUID uuid;
	
	@Column(name = "numpedcomp")
	private Integer numpedcomp;
	
	@Column(name = "dtpedido")
	private Date dtPedido;
	
	@Column(name = "vltotal")
	private BigDecimal vlTotal;
	
	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

}

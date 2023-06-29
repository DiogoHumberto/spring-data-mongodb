package com.springmongodb.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmongodb.model.PedidoModel;

public interface PedidoRepository extends JpaRepository <PedidoModel, UUID>{

	Optional<PedidoModel> findByNumpedcomp(Integer numpedcomp);
	
	boolean existsByNumpedcomp(Integer numpedcomp);
}

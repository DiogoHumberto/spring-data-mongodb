package com.springmongodb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springmongodb.model.PedidoModel;

public interface PedidoRepository extends JpaRepository <PedidoModel, UUID>{

}

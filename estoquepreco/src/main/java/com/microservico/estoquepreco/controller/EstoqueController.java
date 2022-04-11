package com.microservico.estoquepreco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.microservico.estoquepreco.service.RabbitmqService;

import constantes.*;
import dto.*;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {
	
	@Autowired
	private RabbitmqService rabbitmqService;
	
	@PutMapping
	private ResponseEntity alertaEstoque(@RequestBody EstoqueDto estoqueDto) {
		System.out.println(estoqueDto.codigoProduto);
		this.rabbitmqService.enviaMensagem(RabbitMQConstantes.FILA_ESTOQUE, estoqueDto);
		return new ResponseEntity(HttpStatus.OK);
	}

}

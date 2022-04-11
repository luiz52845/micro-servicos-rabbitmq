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
@RequestMapping(value = "/preco")
public class precoController {
	
	@Autowired
	private RabbitmqService rabbitmqService;
	
	@PutMapping
	private ResponseEntity alertaPreco(@RequestBody PrecoDto precoDto) {
		
		this.rabbitmqService.enviaMensagem(RabbitMQConstantes.FILA_PRECO, precoDto);
		return new ResponseEntity(HttpStatus.OK);
	}

}

package com.consumidor.estoquepreco.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import constantes.RabbitMQConstantes;
import dto.EstoqueDto;

@Component
public class EstoqueConsumer {

	@RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE)
	private  void consumidor(EstoqueDto estoqueDto) {
		 System.out.println(estoqueDto.codigoProduto);
		    System.out.println(estoqueDto.quantidade);
		    System.out.println("------------------------------------");
		
	}
}

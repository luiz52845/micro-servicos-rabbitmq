package com.microservico.estoquepreco.conection;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import constantes.*;
import dto.*;

@Component
public class RabbitMQConection {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true , false, false);
	}
	
	private DirectExchange trocaDireta() {
		return new DirectExchange(NOME_EXCHANGE);
	}
	
	private Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}
	
	@PostConstruct
	private void adiciona() {
		Queue filaEstoque = this.fila(RabbitMQConstantes.FILA_ESTOQUE);
		Queue filaPreco = this.fila(RabbitMQConstantes.FILA_PRECO);
		
		DirectExchange troca = this.trocaDireta();
		
		Binding ligaçãoEstoque = this.relacionamento(filaEstoque, troca);
		Binding ligaçãoPreco = this.relacionamento(filaPreco, troca);
		
		
		//criando as filas no RabbitMQ
		this.amqpAdmin.declareQueue(filaEstoque);
		this.amqpAdmin.declareQueue(filaPreco);
		
		this.amqpAdmin.declareExchange(troca);
		
		this.amqpAdmin.declareBinding(ligaçãoEstoque);
		this.amqpAdmin.declareBinding(ligaçãoPreco);
		
		
		
	}
}

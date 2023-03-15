package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread{
	
	Semaphore saque;
	Semaphore deposito;
	int operacao;
	String escolhida;
	int escolhaid=0;
	
	public ThreadBanco(int operacao, Semaphore saque, Semaphore deposito) {
		this.saque = saque;
		this.deposito = deposito;
		this.operacao = operacao;
		
	}
	
	@Override
	public void run() { // Inicia a operação das threads
		iniciarSistema(); // <-- Trata o controle dos semaforos
	}
	
	public void iniciarOperacao() { //Inicia a operação dos caixas
		System.out.println("Conta "+operacao+": "+escolhida);
		fazendoOperacao();
	}
	
	public void definirOperacao() { //Define se a operação vai ser Saque ou Depósito
		int realizar = (int)(Math.random()*2)+1;
		if(realizar==1) {
			escolhida = "Saque";
			escolhaid = 1;
		}else {
			escolhida = "Deposito";
			escolhaid = 2;
		}
	}
	
	public void fazendoOperacao() { //Realiza a operação de saque ou depósito
		if(escolhaid==1) {
			
			int valor = (int) (Math.random()*1001);
			int saldo = 1000;
			
			saldo -= valor;
			
			System.out.println("Valor sacado: "+valor);
			System.out.println("Saldo remanescente: "+saldo);
		}else {
			
			int valor = (int) (Math.random()*1001);
			int saldo = 1000;
			
			saldo += valor;
			
			System.out.println("Valor depositado: "+valor);
			System.out.println("Saldo remanescente: "+saldo);
			
		}
		try {
			Thread.sleep((int)(Math.random()*1001)+1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Caixa "+operacao+": Operacao finalizada");
	}
	
	public void iniciarSistema() { //Organiza o fluxo de operação dos caixas
		definirOperacao();
		if(escolhaid == 1) {
			try {
				saque.acquire();
				iniciarOperacao();
			} catch (Exception e) {

			} finally {
				saque.release();
			}
		} else {
			if(escolhaid == 2) {
				try {
					deposito.acquire();
					iniciarOperacao();
				} catch (Exception e) {
					
				} finally {
					deposito.release();
				}
			}
		}
	}
}

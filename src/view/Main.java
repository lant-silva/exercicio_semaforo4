package view;

import java.util.concurrent.Semaphore;
import controller.ThreadBanco;

public class Main {

	static Semaphore saque;
	static Semaphore deposito;
	
	public static void main(String[] args) {
		
		int operacao = 20;
		saque = new Semaphore(1);
		deposito = new Semaphore(1);
		
		for(int i=1;i<=operacao;i++) {
			Thread operar = new ThreadBanco(i, saque, deposito);
			operar.start();
		}
	}
}

package br.ifpb.edu.pd;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread{
	private DataInputStream in;
	private Socket client;


	public ServerThread (DataInputStream in) {
		this.in = in;
	}
	
	public ServerThread (Socket client) {
		this.client = client;
	}

	public void run() {
		String recebida ="";

		do {
			try {
				System.out.println("estou no trycatch servidor");
				recebida = in.readUTF();
				System.out.println(recebida);
				verificar(recebida);
				
			}catch (IOException e) {
				System.out.println("O segunte erro ocorreu: ");
				e.getMessage();
			}
		}while(!recebida.equals("fim"));
	}

	
	public void sendToAll(DataInputStream in, String msg) {
		
	}
	
	String verificar(String recebida) {
		String aux="";
		String[] splited = recebida.split(" ");
		System.out.println("estou no verificar");
		aux = splited[0];
		
		switch (aux) {
		case ("send"):
			if(splited[1].equals("-all")) {
				System.out.println("estou no all");
			}else
				if(splited[1].equals("-user")) {
					System.out.println("estou no user");
				}
			
			
			
			break;

		default:
			System.out.println("Comando inválido, utilizar apenasos comandos seguintes:");
			System.out.println("send -all(envia mensagem para a sala)");
			System.out.println("send -user(enviar mensagem para um usuario especifico)");
			System.out.println("bye (sair do grupo)");
			System.out.println("list (listar usuários na sala)");
			System.out.println("rename (renomear)");
			break;
		}
		
		return recebida;
	}

}

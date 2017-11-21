package br.ifpb.edu.pd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Server {
	public static void main(String[] args) {
		
		try {
			
			//Criação do gerenciador de clientes através de uma HashMap
			Map<String, Socket> clientMap = new HashMap<String, Socket>();
			
			String recebida="";
			String nome = "";
			ServerSocket serverSocket = new ServerSocket(27015);
			System.out.println("Aberto e escutando");
			Socket socket = serverSocket.accept();
			
			
				System.out.println("Servidor ativo, aguardando conexões!!!");
				
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				nome = in.readUTF();
				clientMap.put(nome, socket);
				
				
				
				new ServerThread(in).start();
				recebida = in.readUTF();
				
				
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Ocorreu o seguinte erro: ");
			e.getStackTrace();
		}
		
	}
}

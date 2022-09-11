//Author: Alexander Dolk
package com.dolk.EncryptedChatServerTCP_IP_Java_Maven;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
	
	ArrayList<ServerThread> serverThreads;
	Iterator<ServerThread> serverThreadsIterator;
	boolean stopServer = false;
	ServerSocket mainServerSocket;
	
	public Server() {
		try {
			this.mainServerSocket = new ServerSocket(4848);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void runServer (){
		serverThreads = new ArrayList<ServerThread>();
		
    	try {
			while(!stopServer) {
				Socket serverThreadSocket = mainServerSocket.accept();
				ServerThread serverThread = new ServerThread(serverThreadSocket, this);
				serverThreads.add(serverThread);
				serverThread.start();
			}
    } catch (Exception ex) {
    	ex.printStackTrace();
    }
	}
	
	void distributeObjectToAllServerThreads(Object object) {
		serverThreadsIterator = serverThreads.iterator();
		ServerThread serverThread;
		while(serverThreadsIterator.hasNext()) {
			try {
				serverThread = (ServerThread) serverThreadsIterator.next();
				serverThread.outputStream.writeObject(object);
			} catch (Exception ex) {
				serverThreadsIterator.remove();
				ex.printStackTrace();
			}
		}
	}
	
	void closeServerSocket() {
		try {
			mainServerSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void stopServer() {
		this.stopServer = true;
	}
}

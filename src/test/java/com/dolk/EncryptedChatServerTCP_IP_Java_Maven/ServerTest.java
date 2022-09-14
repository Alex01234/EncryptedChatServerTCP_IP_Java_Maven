//Author: Alexander Dolk
package com.dolk.EncryptedChatServerTCP_IP_Java_Maven;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.junit.jupiter.api.Test;

class ServerTest {
	
	@Test
	void Server_ObjectCreated_ServerSocketExists() {
		final Server server = new Server();
		Thread s_t = new Thread() {
			public void run() {
				server.runServer();
			}
		};
		s_t.start();
		
		assertEquals(4848, server.mainServerSocket.getLocalPort());
		
		server.closeServerSocket();
		server.stopServer();
	}
	
	@Test
	void runServer_ServerRunning_ServerAcceptsConnections() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, InterruptedException {
		final Server server = new Server();
		Thread s_t = new Thread() {
			public void run() {
				server.runServer();
			}
		};
		s_t.start();
		

		final Client client1 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t1 = new Thread() {
			public void run() {
				try {
					client1.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t1.start();

		final Client client2 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t2 = new Thread() {
			public void run() {
				try {
					client2.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t2.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		assertEquals(2, server.serverThreads.size());
		
		server.closeServerSocket();
		server.stopServer();
		client1.disconnect();
		client1.shutDownClient();
		client2.disconnect();
		client2.shutDownClient();
	}
	
	@Test 
	void distributeObjectToAllServerThreads_ConnectedClients_ObjectsReceived() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, InterruptedException {
		final Server server = new Server();
		Thread s_t = new Thread() {
			public void run() {
				server.runServer();
			}
		};
		s_t.start();
		

		final Client client1 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t1 = new Thread() {
			public void run() {
				try {
					client1.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t1.start();

		final Client client2 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t2 = new Thread() {
			public void run() {
				try {
					client2.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t2.start();
		
		final Client client3 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t3 = new Thread() {
			public void run() {
				try {
					client3.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t3.start();
		
		
		client1.sendMessage("Hello from client1");
		TimeUnit.SECONDS.sleep(1);
		
		assertEquals("Hello from client1", client2.receivedMessage);
		assertEquals("Hello from client1", client3.receivedMessage);
		
		server.closeServerSocket();
		server.stopServer();
		client1.disconnect();
		client1.shutDownClient();
		client2.disconnect();
		client2.shutDownClient();
		client3.disconnect();
		client3.shutDownClient();
	}
	
	@Test 
	void distributeObjectToAllServerThreads_OneClientDisconnects_DisconnectedClientIsRemovedAndConnectedClientReceivesMessage() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, InterruptedException {
		final Server server = new Server();
		Thread s_t = new Thread() {
			public void run() {
				server.runServer();
			}
		};
		s_t.start();
		

		final Client client1 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t1 = new Thread() {
			public void run() {
				try {
					client1.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t1.start();

		final Client client2 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t2 = new Thread() {
			public void run() {
				try {
					client2.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t2.start();
		
		final Client client3 = new Client("localhost", 4848, "0JZGv0hwh7PiU548", "95FdIBeP46LyIo2k");
		Thread c_t3 = new Thread() {
			public void run() {
				try {
					client3.connectClient();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		c_t3.start();
		
		TimeUnit.SECONDS.sleep(1);
		assertEquals(3, server.serverThreads.size());
		client3.disconnect();
		client3.shutDownClient();
		TimeUnit.SECONDS.sleep(1);
		client1.sendMessage("Hello from client1");
		TimeUnit.SECONDS.sleep(1);
		assertEquals("Hello from client1", client2.receivedMessage);
		assertEquals(2, server.serverThreads.size());
		
		server.closeServerSocket();
		server.stopServer();
		client1.disconnect();
		client1.shutDownClient();
		client2.disconnect();
		client2.shutDownClient();
	}


}

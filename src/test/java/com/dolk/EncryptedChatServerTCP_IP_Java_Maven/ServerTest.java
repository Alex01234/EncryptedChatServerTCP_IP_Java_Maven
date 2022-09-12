//Author: Alexander Dolk
package com.dolk.EncryptedChatServerTCP_IP_Java_Maven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* 
 * Test distributeObjectToAllServerThreads success:
 * 		- That all objects in serverThreads receive objects from the server
 * Test distributeObjectToAllServerThreads missing serverThread from serverThreads:
 * 		- That a previously removed serverThread generates an exception and the object is removed
 * Test closeServerSocket:
 * 		- That mainServerSocket is closed
 * Test stopServer
 * 		- That stopServer becomes true
 * */
class ServerTest {
	
//	@Test
//	void Server_ObjectCreated_ServerSocketExists() {
//		Server server = new Server();
//		assertEquals(4850, server.mainServerSocket.getLocalPort());
//		server.closeServerSocket();
//		server.stopServer();
//	}
	
	@Test
	void runServer_ServerRunning_ServerAcceptsConnections() {
		final Server server = new Server();
		
		Thread t = new Thread() {
			public void run() {
				server.runServer();
			}
		};
		t.start();
		
		//Assert that ArrayList serverThreads is created
		assertEquals(true, server.serverThreads.isEmpty());
		//Create client, connect it
		//Assert serverThreads size is 1
		//Create another client, connect it
		//Assert serverThreads size is 2
		server.closeServerSocket();
		server.stopServer();
	}

//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}

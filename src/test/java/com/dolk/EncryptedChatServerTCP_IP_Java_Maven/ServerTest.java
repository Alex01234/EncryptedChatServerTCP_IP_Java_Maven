//Author: Alexander Dolk
package com.dolk.EncryptedChatServerTCP_IP_Java_Maven;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* Test constructor success
 * Test runServer, verify:
 * 		- ArrayList serverThreads created
 * 		- mainServerSocket accepts connections
 * 		- size of serverThreads increase with new connections
 * 		- new serverThread started
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

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}

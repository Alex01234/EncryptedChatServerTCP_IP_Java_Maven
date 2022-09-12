package com.dolk.EncryptedChatServerTCP_IP_Java_Maven;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Client {

	InetAddress host;
	int port;
	Socket socket;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	Thread receivingThread;
	final AtomicBoolean running = new AtomicBoolean(false);
	String firstPassword;
	String secondPassword;
	String receivedMessage;
	
	public Client(String hostAsString, int port, String firstPassword, String secondPassword) throws IOException {
		this.host = InetAddress.getByName(hostAsString);
		this.port = port;
		this.firstPassword = firstPassword;
		this.secondPassword = secondPassword;
		this.socket = new Socket(this.host, this.port);
		this.socket.setKeepAlive(true);
	}
	
	void connectClient() throws IOException {
		outStream = new ObjectOutputStream(socket.getOutputStream());
		startReceiver();
	}
	
	void startReceiver() {
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					receivingTask();
				} catch(Exception e) {}
			}
		};

		receivingThread = new Thread(runnable);
		receivingThread.setDaemon(true);
		receivingThread.start();
	}
	
	void receivingTask() throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		running.set(true);
		inStream = new ObjectInputStream(socket.getInputStream());
		while(running.get()) {
			SealedObject sealedObject = (SealedObject) inStream.readObject();

			IvParameterSpec iv = new IvParameterSpec(firstPassword.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(secondPassword.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			String decryptedText = (String) sealedObject.getObject(cipher);
			receivedMessage = decryptedText;
		}
	}
	
	void sendMessage(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, IOException {
		Serializable object = message;

		IvParameterSpec iv = new IvParameterSpec(firstPassword.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(secondPassword.getBytes("UTF-8"),
				"AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

		SealedObject sealedObject = new SealedObject(object, cipher);
		outStream.writeObject(sealedObject);
	}
	
	void disconnect() throws IOException {
		receivingThread.stop();
		socket.close();
	}
	
	void shutDownClient() {
		running.set(false);
	}
}

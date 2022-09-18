# EncryptedChatServerTCP_IP_Java_Maven
## Description
The chat server is a Java program, which accepts connections from chat clients using the TCP/IP protocol and redistributes messsages sent to the server to all connected clients. The chat server is designed to accepts and propagate encrypted messages from the clients. As such, the server receives and sends [objects](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) using the [ObjectInputStream.readObject()](https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html) method, from and to clients. An example of such client programs is the [EncryptedChatClient](https://github.com/Alex01234/EncryptedChatClientTCP_IP_Java_Maven) that I have a written. In the documentation of the aforementioned chat client, one can also see the demonstrantion of the chat server in action.

## Testing and test coverage 

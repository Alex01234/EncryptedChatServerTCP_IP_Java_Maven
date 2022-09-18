# EncryptedChatServerTCP_IP_Java_Maven
## Description
The chat server is a Java program, which accepts connections from chat clients using the TCP/IP protocol and redistributes messages sent to the server to all connected clients. The chat server is designed to accepts and propagate encrypted messages from the clients. As such, the server receives and sends [objects](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) using the [ObjectInputStream.readObject()](https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html) method, from and to clients. An example of such client programs is the [EncryptedChatClient](https://github.com/Alex01234/EncryptedChatClientTCP_IP_Java_Maven) that is designed to be used in combination with this program. In the documentation of the aforementioned chat client, one can also see a demonstration of the chat server in action.

## Testing and test coverage 
Unit tests have been written using [JUnit 5](https://junit.org/junit5/). The tests cover the class Server by 93% and the class ServerThread by 100%. 
![Image showing test coverage of the project.](https://github.com/Alex01234/EncryptedChatServerTCP_IP_Java_Maven/blob/master/EncryptedChatServer_test_coverage.PNG?raw=true)

## Technicalities
The program has been built using Maven.

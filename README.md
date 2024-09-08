## Servidor de Chat - Envio
Realizar un servidor y cliente de chat que contemple las funciones de envio de mensajes. 
El cliente se conecta al servidor, se identifica el usuario y luego empieza a enviar mensajes indicando: USUARIO-DESTINO, MENSAJE.
El servidor debe utilizar el protocolo TCP y soportar múltiples clientes (Multi-hilo)
Deben estar en proyectos independientes el cliente y servidor.

###  Ejecutar el servidor
* Dentro del directorio `tcp-server` ejecutar el siguiente comando:
````
mvn clean package
````
Una vez compilado ejecutar:
```
mvn exec:java "-Dexec.mainClass=py.una.server.TCPServer"
```
O si no se tiene configurado `mvn exec:java` ejecutar el JAR:
```
java -cp target/tcp-server-1.0-SNAPSHOT.jar py.una.server.TCPServer
```
###  Ejecutar el cliente
* Dentro del directorio `tcp-client` ejecutar el siguiente comando:
````
mvn clean package
````
Una vez compilado ejecutar:
```
mvn exec:java "-Dexec.mainClass=py.una.client.TCPClient"
```
O si no se tiene configurado `mvn exec:java` ejecutar el JAR:
```
java -cp target/tcp-client-1.0-SNAPSHOT.jar py.una.client.TCPClient
```

**Notas adicionales:** ```mvn clean``` dentro de cada directorio para limpiar el target.
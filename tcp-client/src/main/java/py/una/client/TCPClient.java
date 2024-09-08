package py.una.client;

import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            unSocket = new Socket("localhost", 4444);
            // Enviamos nosotros
            out = new PrintWriter(unSocket.getOutputStream(), true);

            // Viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexión al host");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        // Leer y mostrar el mensaje de bienvenida del servidor
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Servidor: " + fromServer);

            // Pedir al usuario el nombre de usuario y destino
            System.out.print("Ingrese su nombre de usuario: ");
            String userName = stdIn.readLine();
            System.out.print("Ingrese el destinatario: ");
            String recipient = stdIn.readLine();
            
            System.out.println("Escriba su mensaje. (Para salir, escriba 'Bye')");

            // Leer el mensaje del usuario y enviarlo al servidor
            while ((fromUser = stdIn.readLine()) != null) {
                if (fromUser.equalsIgnoreCase("Bye")) {
                    break;
                }
                
                String message = userName + "-" + recipient + ", " + fromUser;
                out.println(message);
                
                // Mostrar la respuesta del servidor
                fromServer = in.readLine();
                if (fromServer != null) {
                    System.out.println("Servidor: " + fromServer);
                }
            }
            break;
        }

        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
}

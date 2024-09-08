package py.una.server;

import java.util.concurrent.TimeUnit;
import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String[] args) throws Exception {

        int puertoServidor = 4444;
        int tiempo_procesamiento_miliseg = 2000;
		
        try {
            tiempo_procesamiento_miliseg = Integer.parseInt(args[0]);
        } catch (Exception e1) {
            System.out.println("Se omite el argumento, tiempo de procesamiento " + tiempo_procesamiento_miliseg + ". Ref: " + e1);
        }
		
        try (ServerSocket serverSocket = new ServerSocket(puertoServidor)) {
            System.out.println("Puerto abierto: " + puertoServidor + ".");
            while (true) {
                new ClientHandler(serverSocket.accept(), tiempo_procesamiento_miliseg).start();
            }
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: " + puertoServidor + ".");
            System.exit(1);
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private int tiempo_procesamiento_miliseg;

        public ClientHandler(Socket socket, int tiempo_procesamiento_miliseg) {
            this.clientSocket = socket;
            this.tiempo_procesamiento_miliseg = tiempo_procesamiento_miliseg;
        }

        public void run() {
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                out.println("Bienvenido!");
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + inputLine);
                    String outputLine = "Respuesta igual al recibido: " + inputLine;

                    TimeUnit.MILLISECONDS.sleep(tiempo_procesamiento_miliseg);

                    out.println(outputLine);
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error manejando al cliente: " + e.getMessage());
            }
        }
    }
}

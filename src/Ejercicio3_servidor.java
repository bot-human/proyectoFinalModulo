import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Ejercicio3_servidor {
    public static void main(String[] args) {
        int puerto = 8080;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor en la escucha ..." + puerto);

            while (true) {
                Socket socket = serverSocket.accept();
                new respuestaServidor(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class respuestaServidor extends Thread {
        private Socket socket;

        public respuestaServidor(Socket socket){
                this.socket=socket;

        }

        @Override
        public void conecta() {
            try (
                    DataInputStream entrada = new DataInputStream(socket.getInputStream());
                    DataInputStream salida = new DataInputStream(socket.getOutputStream()
                    )
            {
                String fechaTransaccion = entrada.readUTF();
                String numTarjeta = entrada.readUTF();
                String fechavenc = entrada.readUTF();
                String cvv = entrada.readUTF();
                String cantidad = entrada.readUTF();

                //cod activacion
                Random rand = new Random();
                int codAleatorio = rand.nextInt(4);
                String codigoAutorizado;


                switch (codAleatorio){
                    case 0:
                        codigoAutorizado = "0000"; //transacion acepta
                        break;
                    case 1:
                        codigoAutorizado = "9137"; //Error al validar
                        break;
                    case 2:
                        codigoAutorizado = "9221"; // no esta introduciendo el cvv correto
                        break;
                    case 3:
                        codigoAutorizado = "9677"; //Saldo insuficiente
                    default:
                        codigoAutorizado = "1111";
                        break;
                }
                salida.writeUTF(fechaTransaccion);
                salida.writeUTF(numTarjeta);
                salida.writeUTF(codigoAutorizado);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio3_cliente {
    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 8080;

        try (Socket socket = new Socket(servidor, puerto)) {
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataInputStream salida = new DataInputStream(socket.getOutputStream());
            Scanner e1 = new Scanner(System.in);

            //pedi datos al usuario

            System.out.println("Fecha transacion (AAAAMMDD): ");
            String fechaTransaccion = e1.nextLine();

            System.out.println("Num tarjeta: ");
            String numTarjeta = e1.nextLine();

            System.out.println("Fecha vencimiento (AAAAMMDD): ");
            String fechaVencimiento = e1.nextLine();

            System.out.println("CVV: ");
            String cvv = e1.nextLine();

            System.out.println("Cantidad: ");
            String cantidad = e1.nextLine();

            //enviar datos al servidor

            salida.writeUTF(fechaTransaccion);
            salida.writeUTF(numTarjeta);
            salida.writeUTF(fechaVencimiento);
            salida.writeUTF(cvv);
            salida.writeUTF(cantidad);

            String respuestaFechaTransacion = entrada.readUTF();
            String respuestaNumTarjeta = entrada.readUTF();
            String respuestaAutorizacion = entrada.readUTF();

            switch (codigoAutorizacion) {
                case "0000":
                    System.out.println("Transacion acepta.");
                    break;
                case "9137":
                    System.out.println("Error al validar tarjeta.");
                    break;
                case "9221":
                    System.out.println("El cliente no esta introduciendo el cvv.");
                    break;
                case "9677":
                    System.out.println("Saldo insuficiente.");
                    break;
                default:
                    System.out.println("Cod de autorizacion desconocido.");
                    break;
            }

            }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

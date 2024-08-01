import java.io.*;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        //obtener un fichero
        Scanner cifrarFrichero = new Scanner(System.in);

        // Obtener desde consola el fichero a cifrar y el fichero destino
        System.out.print("Introduce el nombre del fichero de origen: ");
        String ficheroOrigen = cifrarFrichero.nextLine();
        System.out.print("Introduce el nombre del fichero de destino: ");
        String ficheroDestino = cifrarFrichero.nextLine();

        // Proceso de cifrado
        System.out.print("Introduce un cifrado entre 1 hasta 255: ");
        int valorCifrado = cifrarFrichero.nextInt();

        // Validar el cifrado
        if (valorCifrado < 1 || valorCifrado > 255) {
            System.out.println("El valor debe estar entre 1 y 255.");
            return;
        }

        // Procesar el fichero
        try (BufferedReader reader = new BufferedReader(new FileReader(ficheroOrigen));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroDestino))) {

            int caracter;
            while ((caracter = reader.read()) != -1) {
                // Aplicar la operación XOR
                int caracterCifrado = caracter ^ valorCifrado;
                writer.write(caracterCifrado);
            }

            System.out.println("El fichero ha sido cifrado correctamente.");

        } catch (IOException e) {
            System.out.println("Ocurrió un error al procesar el fichero: " + e.getMessage());
        }
    }
}

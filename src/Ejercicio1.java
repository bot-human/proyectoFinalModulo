import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner introducirNombreFichero = new Scanner(System.in);
        System.out.println("Introduzca la ruta del fichero: ");
        String directorioPath = introducirNombreFichero.nextLine();
        File directorio = new File(directorioPath);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Un fichero ha sido creado en el directorio: " + directorioPath);
            } else {
                System.out.println("No se pudo crear el fichero en el directorio.");
                introducirNombreFichero.close();
                return;
            }
        }

        if (directorio.exists() && directorio.isDirectory()) {
            // Crea el crearFichero example.txt dentro del directorio
            File crearFichero = new File(directorio, "example.txt");
            try {
                if (crearFichero.createNewFile()) {
                    System.out.println("Fichero creado: " + crearFichero.getAbsolutePath());
                } else {
                    System.out.println("El crearFichero ya existe.");
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error al crear el crearFichero.");
                e.printStackTrace();
            }


        if (crearFichero.exists()) {
            System.out.println("El crearFichero fue creado correctamente.");
            System.out.println("Ruta absoluta: " + crearFichero.getAbsolutePath());
            System.out.println("Es un Fichero valido: " + crearFichero.isFile());
            System.out.println("Es un directorio: " + crearFichero.isDirectory());

            // Mostrar información del fichero
            System.out.println("Nombre: " + crearFichero.getName());
            System.out.println("Tamaño: " + crearFichero.length() + " bytes");
            System.out.println("Permisos de lectura: " + crearFichero.canRead());
            System.out.println("Permisos de escritura: " + crearFichero.canWrite());
        } else {
            System.out.println("El Fichero no fue creado.");
        }
    } else {
        System.out.println("El directorio no existe o no es un directorio.");
    }

        introducirNombreFichero.close();

    }
}

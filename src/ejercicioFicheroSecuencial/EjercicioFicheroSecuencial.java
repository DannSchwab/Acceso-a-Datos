package ejercicioFicheroSecuencial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class EjercicioFicheroSecuencial {


    static final int MIN = 0;
    static final int MAX = 100;
    static final String ARCHIVO_RECORD = "record.dat";
    static Scanner sc = new Scanner(System.in);
    
    public static int random() {
        return (int)(Math.random() * (MAX - MIN + 1)) + MIN;
    }
    
    // Lectura del fichero
    public static int leerRecord() {
        // try-with-resources cierra el stream automáticamente
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ARCHIVO_RECORD))) {
            return dis.readInt();
        } catch (IOException e) {
            return Integer.MAX_VALUE; 
        }
    }
    
    // Escritura del fichero
    public static void guardarRecord(int intentos) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ARCHIVO_RECORD))) {
            dos.writeInt(intentos);
        } catch (IOException e) {
            System.out.println("Error al guardar el récord en el archivo.");
        }
    }
    
    public static void main (String[]args) {
        boolean seguir = true;
        // Lee el récord al iniciar el programa
        int mejorPuntuacion = leerRecord(); 
        
        System.out.println("¡Adivina el número entre " + MIN + " y " + MAX + "!");
        if (mejorPuntuacion != Integer.MAX_VALUE) {
            System.out.println("(Récord actual: " + mejorPuntuacion + " intentos)");
        }
        
        while(seguir) {
            boolean acierto = false;
            int intentos = 0; 
            int numero = random(); 
            
            while(!acierto) {
                System.out.print("Introduce tu número: ");
                int respuesta = sc.nextInt();
                sc.nextLine(); 
                
                intentos++;
                
                if (respuesta < numero) {
                    System.out.println("Tu número está por debajo");
                } else if (respuesta > numero) {
                    System.out.println("Tu número está por encima");
                } else {
                    System.out.println("¡ACERTASTE!");	
                    acierto = true;
                    System.out.println("Hiciste " + intentos + " intentos");
                    
                    // Comprueba si hay un nuevo récord
                    if (intentos < mejorPuntuacion) {
                        mejorPuntuacion = intentos;
                        guardarRecord(mejorPuntuacion);
                        System.out.println("🌟 ¡NUEVO RÉCORD GUARDADO! 🌟");
                    } else {
                        System.out.println("No superaste el récord de " + mejorPuntuacion + " intentos.");
                    }
                }
            }
            
            System.out.println("¿Quiere continuar? (y/n)");	
            String opcion = sc.nextLine().trim().toLowerCase(); 
            
            if (opcion.equals("n")) {
                seguir = false;
                System.out.println("¡Gracias por jugar!");
            }
        }
        
        sc.close();
    }
	
}

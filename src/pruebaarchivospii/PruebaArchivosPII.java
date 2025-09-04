/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebaarchivospii;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author adrianaguilar
 */
public class PruebaArchivosPII {

    /**
     * @param args the command line arguments
     */
    
    private static final String ArchivoPrincipal = "tareas.txt";
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do{
            inicio();
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    agregarTarea(sc);
                    break;
                    
                case 2:
                    mostrarTarea();
                    break;
                case 3:
                    completarTarea(sc);
                    break;
                 case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opion no valida. ");
            }
                   
            } while (opcion != 4);

            sc.close();
        }
        
        
        
        private static void inicio() {
            
            System.out.println("GESTOR DE TAREAS");
            System.out.println("===============");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Mostrar tareas");
            System.out.println("3. Completar tarea");
            System.out.println("4. Salir");
            System.out.println("Seleccione una opcion: ");
            
        }
        
        private static void agregarTarea(Scanner sc){
            System.out.println("AGREGAR TAREA");
            System.out.println("==============");
            System.out.println("Ingresa una nueva tarea: ");
            String tarea = sc.nextLine();
            
            
            
            try (FileWriter escribir = new FileWriter(ArchivoPrincipal, true)){
                escribir.write("[ ] " + tarea + "\n");
                System.out.println("✓ Tarea Agregada: " + tarea);
            } catch (IOException e){
                System.out.println("Error al agregar la tarea.");
            }
        }
        
        private static void mostrarTarea() {
            System.out.println("LISTA DE TAREAs");
            System.out.println("============");
            
            
            try(FileReader leer = new FileReader(ArchivoPrincipal)){
                int numero = 1;
                int c;
                String linea = "";
                
                while ((c = leer.read()) != -1) {
                    if (c == '\n') {
                        if (!linea.trim().equals("")) { 
                            System.out.println(numero + ". " + linea.trim()); 
                            numero++;
                        }
                        linea = "";
                    } else if (c != '\r') { 
                        linea += (char) c;
                    }
                }

               
                if (!linea.trim().equals("")) {
                    System.out.println(numero + ". " + linea.trim());
                }
                
             if (!linea.equals("")) {
                 System.out.println(numero + ". " + linea);
                 
             }
            }catch(IOException e){
            System.out.println("ERROR, Esto no deberia de pasar. ");
        }
        }
    
        
        
        
        
        
        
        private static void completarTarea(Scanner sc) {
        System.out.print("\nNúmero de tarea a completar: ");
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un número válido: ");
            sc.next();
        }
        int numeroObjetivo = sc.nextInt();
        sc.nextLine(); 

        try {
            File archivo = new File(ArchivoPrincipal);
            if (!archivo.exists()) {
                System.out.println("No hay tareas registradas.");
                return;
            }

            FileReader leer = new FileReader(archivo);

            String contenido = "";
            String linea = "";
            int c;
            int indice = 1;
            String tareaCompletada = "";

            while ((c = leer.read()) != -1) {
                if (c == '\n') {
                    if (indice == numeroObjetivo) {
                        linea = linea.replace("[ ]", "[✓]");
                        tareaCompletada = linea;
                    }
                    contenido += linea + "\n";
                    linea = "";
                    indice++;
                } else if (c != '\r') {
                    linea += (char) c;
                }
            }
           
            if (!linea.equals("")) {
                if (indice == numeroObjetivo) {
                    linea = linea.replace("[ ]", "[✓]");
                    tareaCompletada = linea;
                }
                contenido += linea + "\n";
            }
            leer.close();

            FileWriter escribir = new FileWriter(archivo);
            escribir.write(contenido);
            escribir.close();

            if (!tareaCompletada.equals("")) {
                
                String nombreTarea = tareaCompletada.length() >= 4 ? tareaCompletada.substring(4) : tareaCompletada;
                System.out.println("\n✓ Tarea #" + numeroObjetivo + " completada: " + nombreTarea);
            } else {
                System.out.println("Número de tarea inválido.");
            }
        } catch (IOException e) {
            System.out.println("Error al completar la tarea.");
        }
    }
}







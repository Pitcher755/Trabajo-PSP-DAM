
package concesionariopsp;

import java.util.concurrent.Semaphore;

/**
 * Esta aplicación gestiona el acceso concurrente a coches de prueba de un 
 * concesionario. Utiliza hilos y semáforos para permitir que varios clientes 
 * prueben coches de manera controlada.
 * <p>
 * La aplicación simula un concesionario con cuatro coches disponibles al mismo
 * tiempo para que los prueben nueve clientes. Cada cliente intenta accedera un
 * coche y espera su turno si todos están ocupados.
 * 
 * @version 1.0
 * @author Pitcher
 */
public class Concesionario {

    /**
     * Método principal donde se crean los clientes y se gestiona el acceso a los coches.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Constante que define el número de coches disponíbles que se pueden probar
        final int MAX_COCHES = 4;
        
        // Semáforo con los permisos según el número de coches.
        Semaphore semaforo = new Semaphore(MAX_COCHES);
        
        // Array con los 9 clientes que quieren probar los coches.
        String[] nombreClientes = {"Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4",
            "Cliente 5", "Cliente 6", "Cliente 7", "Cliente 8", "Cliente 9"};
        
        // bucle for each que crea e inicia un hilo para cada cliente del Array nombreClientes.
        for(String nombre : nombreClientes){
            new Cliente(nombre, semaforo).start();
        }
    }
    
}

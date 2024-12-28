
package concesionariopsp;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Esta clase representa a un cliente que quiere probar un coche en nuestro concesionario.
 * Cada cliente representa un hilo que utiliza un semáforo para controlar el acceso a los 
 * vehículos que hay disponibles.
 * @author JFG
 */
public class Cliente extends Thread {
    // Atributos de la clase Cliente
    private String nombre;  // Nombre del cliente
    private int idCoche; // ID del coche que va a probar el cliente
    private Semaphore semaforo; // Semáforo que controla el acceso a los coches.

    /**
     * Costructor con parámetros (nombre y semaforo) de la clase Cliente.
     * @param nombre Nombre del cliente.
     * @param semaforo Semáforo que controla el acceso
     */
    public Cliente(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
    }
    
    /**
     * Metodo run() con el código que se ejecuta al iniciar un hilo.
     * Intenta probar un coche y deja libre el recurso cuando termina de probar.
     */
    @Override
    public void run(){
        try {
            // El cliente intenta probar un coche.
            semaforo.acquire(); // Adquiere el coche y el semáforo queda rojo (ocupado).
            
            // Dar aleatoriamente un ID de coche con Math.random del 1 al 4.
            idCoche = (int)(Math.random() * 4) + 1;
            
            // Muestra el mensaje cuando el cliente empieza a probar un coche.
            System.out.println(nombre + "... está probando el coche " + idCoche + " ...");
            
            // Tiempo aleatorio que tarda en probar el coche (entre 1 y 5 sgundos).
            Thread.sleep((int)(Math.random() * 5000) + 1000);
            
            // Muestra en pantalla el mensaje de que el cliente ha terminado de usar el coche.
            System.out.println(nombre + "... terminó de probar el coche " + idCoche + " ...");
        
        } catch (InterruptedException ex) {
            // Captura cualquier error de iterrupción
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, "Error de interrupción del hilo", ex);
        } finally {
            // Libera el coche dejando el semáforo libre para que otro cliente pueda probar el coche
            semaforo.release();
        }
    }    
}

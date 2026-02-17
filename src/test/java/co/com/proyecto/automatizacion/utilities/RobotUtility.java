package co.com.proyecto.automatizacion.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Robot;
import java.awt.AWTException;

/**
 * Utilidad para simular eventos de teclado y mouse a nivel del sistema operativo.
 * 
 * Esta clase usa java.awt.Robot para generar eventos de teclado que pueden
 * interactuar con diálogos del sistema operativo (como el diálogo de selección
 * de archivos).
 * 
 * Útil para:
 * - Subir archivos cuando sendKeys() no funciona
 * - Interactuar con diálogos nativos del sistema
 * - Simular combinaciones de teclas (Ctrl+V, Alt+Tab, etc.)
 * 
 * Patrón Singleton: Solo hay una instancia de Robot (estática) que se comparte.
 */
public class RobotUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotUtility.class);

    private static Robot robot;

    static {
        try {
            // Crea una instancia de Robot para generar eventos de teclado/mouse
            robot = new Robot();
        } catch (AWTException e) {
            // Si no se puede crear (raro, pero posible), registra el error
            LOGGER.error("No se pudo inicializar el robot.", e);
        }
    }

    private RobotUtility() {
        // Constructor privado = clase de utilidad (solo métodos estáticos)
    }

    public static void pressAndRelease(int keyEventValue) {
        robot.keyPress(keyEventValue);   // Presiona la tecla
        robot.keyRelease(keyEventValue); // Suelta la tecla
    }

    public static void pressManyKeys(int... keyEventValues) {
        // Presiona cada tecla en el orden especificado
        for (int keyCode : keyEventValues) {
            robot.keyPress(keyCode);
        }
    }

    public static void releaseManyKeys(int... keyEventValues) {
        // Suelta cada tecla en el orden especificado
        for (int keyCode : keyEventValues) {
            robot.keyRelease(keyCode);
        }
    }

    public static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LOGGER.error("Error al esperar.", e);
            // Restaura el estado de interrupción del hilo
            Thread.currentThread().interrupt();
        }
    }

}

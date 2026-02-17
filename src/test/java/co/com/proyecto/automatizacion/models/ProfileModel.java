package co.com.proyecto.automatizacion.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Modelo de datos que representa la información del perfil de un usuario.
 * 
 * Este modelo se usa para transferir datos desde los escenarios Gherkin
 * (escritos en tablas) hacia el código Java.
 * 
 * Lombok genera automáticamente:
 * - @Getter: Métodos getter para todos los campos (getNombre(), getApellido(), etc.)
 * - @AllArgsConstructor: Constructor que acepta todos los campos como parámetros
 * - @ToString: Método toString() que muestra todos los campos
 * 
 * Esto reduce significativamente el código boilerplate que normalmente
 * se necesitaría escribir manualmente.
 */

@Getter                    // Genera getters para todos los campos
@AllArgsConstructor        // Genera constructor con todos los parámetros
@ToString                  // Genera método toString() con todos los campos
public class ProfileModel {

    private final String nombre;
    private final String apellido;
    private final String fechaNacimiento;
    private final String pais;
    private final String genero;
    
    // Gracias a Lombok, no necesitamos escribir:
    // - Constructores
    // - Getters
    // - toString()
    // - equals() y hashCode() (si fuera necesario)
}

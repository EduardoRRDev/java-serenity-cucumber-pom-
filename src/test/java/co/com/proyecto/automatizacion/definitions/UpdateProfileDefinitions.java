package co.com.proyecto.automatizacion.definitions;

import co.com.proyecto.automatizacion.models.ProfileModel;
import co.com.proyecto.automatizacion.steps.LoginWappiSteps;
import co.com.proyecto.automatizacion.steps.UpdateProfileWappiSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Transpose;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Step Definitions para los escenarios de actualización de perfil.
 * 
 * Este archivo demuestra el uso avanzado de Cucumber con:
 * - DataTables: Para pasar datos estructurados desde Gherkin
 * - @DataTableType: Para convertir automáticamente tablas en objetos Java
 * - @Transpose: Para cambiar la orientación de las tablas (filas ↔ columnas)
 * 
 * Los escenarios de actualización de perfil requieren:
 * 1. Login previo con credenciales dinámicas
 * 2. Navegación al formulario de perfil
 * 3. Llenado de campos con datos del escenario
 * 4. Validación del mensaje de éxito
 */

public class UpdateProfileDefinitions {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProfileDefinitions.class);

    @Steps
    private LoginWappiSteps loginWappiSteps;

    @Steps
    private UpdateProfileWappiSteps updateProfileWappiSteps;

    @Dado("que el usuario inicia sesión con las credenciales")
    public void iniciarSesion(@Transpose DataTable dataTable) {
        // Convierte la tabla en un Map donde las claves son los nombres de las columnas
        Map<String, String> informacionInicioSesion = dataTable.asMap();
        
        // Registra las credenciales en el log (útil para debugging)
        informacionInicioSesion.forEach((key, value) -> LOGGER.info("k:{}, v:{}", key, value));

        // Ejecuta el flujo completo de login
        loginWappiSteps.openLoginPage();
        loginWappiSteps.enterCredentials(
            informacionInicioSesion.get("usuario"),      // Extrae "usuario" de la tabla
            informacionInicioSesion.get("claveAcceso")   // Extrae "claveAcceso" de la tabla
        );
        loginWappiSteps.accessSystem();
        loginWappiSteps.validateSuccessfulLogin();
    }

    @Cuando("actualiza la información del perfil")
    public void actualizarInformacionPerfil(ProfileModel informacionPerfil) {
        LOGGER.info("Inicia la actualización del perfil");
        LOGGER.info(informacionPerfil.toString());  // Registra los datos que se van a actualizar
        
        // Navega al formulario de actualización
        updateProfileWappiSteps.goToUpdateProfileForm();
        
        // Actualiza la información con los datos del modelo
        updateProfileWappiSteps.updateInformation(informacionPerfil);
    }

    @Entonces("debería ver el mensaje")
    public void validarActualizacionExitosa(List<String> mensajesEsperados) {
        LOGGER.info(mensajesEsperados.toString());
        updateProfileWappiSteps.validarActualizacion(mensajesEsperados);
    }

    /**
     * Convierte automáticamente una tabla de Cucumber en un objeto ProfileModel.
     * 
     * Este método se ejecuta automáticamente cuando Cucumber encuentra una tabla
     * en un paso que espera un ProfileModel como parámetro.
     * 
     * @DataTableType: Indica a Cucumber que use este método para convertir tablas
     *                 en objetos ProfileModel automáticamente.
     * 
     * @param entry Map con los datos de la tabla (claves = nombres de columnas)
     * @return ProfileModel creado con los datos de la tabla
     * 
     * Ejemplo de conversión:
     * Tabla Gherkin:
     *   | nombre | apellido | fechaNacimiento | pais | genero |
     *   | Arnold | ...      | ...             | ...  | M      |
     * 
     * Se convierte en:
     *   new ProfileModel("Arnold", "...", "...", "...", "M")
     */
    @DataTableType
    public ProfileModel defineProfileModel(@Transpose Map<String, String> entry) {
        return new ProfileModel(
                entry.get("nombre"),           // Extrae el nombre de la tabla
                entry.get("apellido"),         // Extrae el apellido
                entry.get("fechaNacimiento"),  // Extrae la fecha de nacimiento
                entry.get("pais"),             // Extrae el país
                entry.get("genero")            // Extrae el género
        );
    }
}

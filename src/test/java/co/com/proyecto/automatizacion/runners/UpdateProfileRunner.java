package co.com.proyecto.automatizacion.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner para ejecutar los escenarios de actualización de perfil.
 * 
 * Similar a LoginRunner, pero enfocado en los escenarios de actualización de perfil.
 * Ejecuta escenarios que incluyen login previo y actualización de información del usuario.
 * 
 * @CucumberOptions:
 * - features: Escenarios de actualización de perfil
 * - glue: Mismos paquetes que LoginRunner (definitions y hooks compartidos)
 * - tags: Solo ejecuta escenarios marcados con @ActualizarPerfilBasico
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/update_profile",
        glue = {"co.com.proyecto.automatizacion.definitions", "co.com.proyecto.automatizacion.hooks"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@ActualizarPerfilBasico",
        plugin = {"pretty"}
)
public class UpdateProfileRunner {

}

package co.com.proyecto.automatizacion.steps;

import co.com.proyecto.automatizacion.pages.interacciones.GeneralInteraction;
import co.com.proyecto.automatizacion.pages.mapeos.wappi.LoginWappiPage;
import co.com.proyecto.automatizacion.pages.mapeos.wappi.MainWappiPage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Steps para el flujo de inicio de sesión en la aplicación Wappi.
 * 
 * Similar a LoginSteps, pero específico para la aplicación Wappi.
 * La diferencia principal es que usa variables de sesión de Serenity
 * para almacenar credenciales y las recupera después para logging.
 */
public class LoginWappiSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginWappiSteps.class);
    private static final String URL_INICIO_SESION = "https://automation-wappi.vercel.app/login";
    private LoginWappiPage loginWappiPage;
    private MainWappiPage mainWappiPage;
    private GeneralInteraction generalInteraction;

    @Step("user open login page")
    public void openLoginPage() {
        // Abre la URL específica usando el método de GeneralInteraction
        generalInteraction.openPage(URL_INICIO_SESION);
        LOGGER.info("user open login page");
    }

    @Step("enter credentials")
    public void enterCredentials(String username, String password) {
        // Guarda las credenciales en variables de sesión de Serenity
        // Esto permite recuperarlas después para logging o validaciones
        Serenity.setSessionVariable("username").to(username);
        Serenity.setSessionVariable("password").to(password);
        
        // Ingresa las credenciales en los campos correspondientes
        loginWappiPage.inputUsername.type(username);
        loginWappiPage.inputPassword.type(password);
        
        // Registra las credenciales en el log
        LOGGER.atInfo()
              .setMessage("enter credentials with username:{} and password:{}.")
              .addArgument(username)
              .addArgument(password)
              .log();
    }

    @Step("access the system")
    public void accessSystem(){
        // Espera a que el botón sea clickeable y luego hace clic
        loginWappiPage.btnLogin.waitUntilClickable().click();
    }

    @Step("validate successful login")
    public void validateSuccessfulLogin() {
        final String mensajeError = "Login was unsuccessful.";
        
        // Recupera las credenciales de las variables de sesión de Serenity
        String username = Serenity.sessionVariableCalled("username");
        String password = Serenity.sessionVariableCalled("password");
        
        // Valida que el título de la página principal sea "Ofertas"
        // Si falla, muestra el mensajeError y el test falla
        assertThat(mensajeError, 
                   mainWappiPage.txtTitleMainPage.waitUntilVisible().getText(), 
                   is(equalTo("Ofertas")));
        
        // Registra las credenciales usadas en el log (útil para debugging)
        LOGGER.info("Sesión con usuario '{}' y contraseña '{}'", username, password);
    }

}

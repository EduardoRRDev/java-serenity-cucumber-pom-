package co.com.proyecto.automatizacion.pages.mapeos.wappi;

import co.com.proyecto.automatizacion.pages.mapeos.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.annotations.findby.How;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Page Object para la página de inicio de sesión de la aplicación Wappi.
 * 
 * Similar a LoginPage, pero específico para la aplicación Wappi.
 * No tiene @DefaultUrl porque se navega manualmente usando GeneralInteraction.openPage().
 */
public class LoginWappiPage extends GeneralPage {

    /**
     * Campo de entrada para el nombre de usuario.
     * 
     * @FindBy(how = How.ID, using = "username"):
     * - Busca un elemento con id="username"
     */
    @FindBy(how = How.ID, using = "username")
    public WebElementFacade inputUsername;

    /**
     * Campo de entrada para la contraseña.
     * 
     * @FindBy(how = How.ID, using = "password"):
     * - Busca un elemento con id="password"
     */
    @FindBy(how = How.ID, using = "password")
    public WebElementFacade inputPassword;

    /**
     * Botón de login.
     * 
     * @FindBy(id = "button-login"):
     * - Forma abreviada de @FindBy(how = How.ID, using = "button-login")
     * - Busca un elemento con id="button-login"
     */
    @FindBy(id = "button-login")
    public WebElementFacade btnLogin;

}

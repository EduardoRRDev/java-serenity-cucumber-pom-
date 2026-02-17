package co.com.proyecto.automatizacion.pages.mapeos.wappi;

import co.com.proyecto.automatizacion.pages.mapeos.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.At;

/**
 * Page Object para la página principal de la aplicación Wappi.
 * 
 * Esta página se muestra después de un login exitoso en Wappi.
 * Contiene el menú de navegación y el título de la página.
 */
@At("https://automation-wappi.vercel.app/home")  // URL de la página principal de Wappi
public class MainWappiPage extends GeneralPage {

    /**
     * Título de la página principal.
     * 
     * @FindBy(xpath = "//h1"):
     * - Busca el primer elemento h1 en la página
     * - Se usa para validar que el login fue exitoso (debe ser "Ofertas")
     */
    @FindBy(xpath = "//h1")
    public WebElementFacade txtTitleMainPage;

    /**
     * Opción del menú para ir al formulario de actualización de perfil.
     * 
     * @FindBy(xpath = "//a[contains(text(), 'Información personal')]"):
     * - Busca un enlace (<a>) cuyo texto contenga "Información personal"
     * - XPath con función contains() permite búsquedas parciales de texto
     * 
     * Este elemento se usa para navegar al formulario de actualización de perfil.
     */
    @FindBy(xpath = "//a[contains(text(), 'Información personal')]")
    public WebElementFacade menuOptionUpdateProfile;

}

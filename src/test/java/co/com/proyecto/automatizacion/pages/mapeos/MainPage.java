package co.com.proyecto.automatizacion.pages.mapeos;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.At;

/**
 * Page Object para la página principal después del login.
 * 
 * Esta página se muestra después de un login exitoso en la aplicación demo de Serenity.
 * 
 * @At: Indica la URL de esta página. Serenity puede usar esto para verificar
 *      que estás en la página correcta.
 */
@At("https://demo.serenity.is/")  // URL de la página principal
public class MainPage extends GeneralPage {

    @FindBy(xpath = "//main//h1")
    public WebElementFacade txtTitleMainPage;

}

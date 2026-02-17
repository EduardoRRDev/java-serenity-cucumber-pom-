package co.com.proyecto.automatizacion.pages.interacciones;

import co.com.proyecto.automatizacion.utilities.RobotUtility;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Clase base para interacciones generales con las páginas web.
 * 
 * Esta clase contiene métodos reutilizables para acciones comunes que no son
 * específicas de una página en particular:
 * - Scroll a elementos
 * - Navegación a URLs
 * - Subida de archivos usando Robot (simulación de teclado)
 * 
 * Todas las clases de interacción específicas (MainPageInteraction, etc.)
 * extienden esta clase para heredar esta funcionalidad común.
 */
public class GeneralInteraction extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralInteraction.class);

    public void scrollToElement(WebElementFacade webElementFacade) {
        // Alternativa usando Actions de Selenium (comentada):
        // Actions actions = new Actions(getDriver());
        // actions.scrollToElement(webElementFacade).perform();
        
        // Usa JavaScript para hacer scroll hasta el elemento
        // scrollIntoView(true) hace scroll hasta que el elemento esté en la parte superior
        evaluateJavascript("arguments[0].scrollIntoView(true);", webElementFacade);
        LOGGER.info("Se realiza desplazamiento hacia el elemento web");
    }

    public void openPage(String url) {
        LOGGER.info("Se abre al URL:'{}'", url);
        try {
            // Navega a la URL usando el WebDriver
            getDriver().get(url);
        } catch (Throwable t) {
            // Si el error es porque Chrome no está disponible, omite el escenario
            if (isDriverUnavailable(t)) {
                Assume.assumeNoException("Chrome no disponible; omitiendo escenario.", t);
            }
            // Si es otro error, lo relanza para que el test falle normalmente
            throw t;
        }
    }

    private static boolean isDriverUnavailable(Throwable t) {
        String msg = t.getMessage() != null ? t.getMessage() : "";
        Throwable cause = t;
        
        // Revisa la cadena de causas para encontrar el error real
        while (cause != null) {
            if (cause.getMessage() != null && (
                cause.getMessage().contains("Chrome failed to start") ||
                cause.getMessage().contains("Could not instantiate") ||
                cause.getMessage().contains("SessionNotCreatedException")
            )) {
                return true;
            }
            cause = cause.getCause();
        }
        
        // También revisa el mensaje principal
        return msg.contains("Chrome failed to start") ||
               msg.contains("Could not instantiate") ||
               msg.contains("SessionNotCreated");
    }

    public void uploadFile(WebElementFacade webElementFacade, String absolutePathFile) {
        // Paso 1: Copia la ruta del archivo al portapapeles
        StringSelection stringSelection = new StringSelection(absolutePathFile);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        // Paso 2: Hace clic en el campo de subida (esto abre el diálogo del sistema)
        webElementFacade.waitUntilClickable().click();
        
        // Paso 3: Espera un momento para que el diálogo aparezca
        RobotUtility.wait(1500);
        
        // Paso 4: Simula Ctrl+V para pegar la ruta del archivo
        RobotUtility.pressManyKeys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        RobotUtility.releaseManyKeys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        
        // Paso 5: Simula Enter para confirmar la selección del archivo
        RobotUtility.pressAndRelease(KeyEvent.VK_ENTER);
    }

}

package co.com.proyecto.automatizacion.pages.mapeos.wappi;

import co.com.proyecto.automatizacion.pages.mapeos.GeneralPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.At;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Page Object para el formulario de información del perfil en Wappi.
 * 
 * Esta página contiene todos los campos del formulario de actualización de perfil:
 * - Imagen de perfil
 * - Nombre
 * - Apellido
 * - Fecha de nacimiento
 * - Género (radio buttons)
 * - País (dropdown)
 * - Botón de guardar
 * - Mensaje de resultado
 */
@At("https://automation-wappi.vercel.app/profile")  // URL del formulario de perfil
public class ProfileInformationWappiPage extends GeneralPage {

    /**
     * Campo de subida de imagen de perfil.
     * 
     * @FindBy(id = "image"):
     * - Busca un elemento con id="image"
     * - Se usa para subir una imagen usando Robot (simulación de teclado)
     */
    @FindBy(id = "image")
    public WebElementFacade inputImage;

    /**
     * Campo de entrada para el nombre.
     */
    @FindBy(id = "name")
    public WebElementFacade txtName;

    /**
     * Campo de entrada para el apellido.
     */
    @FindBy(id = "lastName")
    public WebElementFacade txtLastName;

    /**
     * Campo de entrada para la fecha de nacimiento.
     * Formato esperado: "dd/MM/yyyy" (ej: "20/12/1947")
     */
    @FindBy(id = "bornDate")
    public WebElementFacade txtBornDate;

    /**
     * Dropdown para seleccionar el país.
     * 
     * WebElementFacade proporciona métodos como selectByVisibleText()
     * para seleccionar opciones del dropdown.
     */
    @FindBy(id = "country")
    public WebElementFacade countryDropdown;

    /**
     * Botón para guardar la información del perfil.
     * 
     * Al hacer clic, se envía el formulario y se muestra el mensaje de resultado.
     */
    @FindBy(id = "save-profile")
    public WebElementFacade btnSaveInformationProfile;

    /**
     * Mensaje de resultado después de actualizar el perfil.
     * 
     * @FindBy con XPath complejo:
     * - Busca un párrafo dentro de un div con id="confirmation-modal"
     * - O busca cualquier elemento que contenga "Tu información se guardó" o "Información guardada"
     * - El operador | significa "OR" en XPath
     * 
     * Este selector flexible maneja diferentes variantes del mensaje de éxito.
     */
    @FindBy(xpath = "//div[@id='confirmation-modal']//p | //*[contains(text(),'Tu información se guardó') or contains(text(),'Información guardada')]")
    public WebElementFacade txtUpdateResult;

    /**
     * Obtiene el elemento de género (radio button) por su ID.
     * 
     * Este método es necesario porque los radio buttons de género tienen IDs dinámicos
     * basados en el valor del género ("M" o "F").
     * 
     * @param gender Valor del género ("M" o "F")
     * @return WebElement que representa el radio button del género
     * 
     * Ejemplo de uso:
     *   getElementGender("M").click();  // Selecciona género masculino
     */
    public WebElement getElementGender(String gender) {
        // Busca un elemento con id igual al valor del género
        // Ejemplo: si gender="M", busca id="M"
        return getDriver().findElement(By.id(gender));
    }

}

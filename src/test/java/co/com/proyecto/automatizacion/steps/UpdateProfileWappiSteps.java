package co.com.proyecto.automatizacion.steps;

import co.com.proyecto.automatizacion.models.ProfileModel;
import co.com.proyecto.automatizacion.pages.interacciones.GeneralInteraction;
import co.com.proyecto.automatizacion.pages.mapeos.wappi.MainWappiPage;
import co.com.proyecto.automatizacion.pages.mapeos.wappi.ProfileInformationWappiPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Steps para el flujo de actualización de perfil en la aplicación Wappi.
 * 
 * Estos steps manejan:
 * - Navegación al formulario de actualización
 * - Llenado de todos los campos del formulario (incluyendo subida de imagen)
 * - Validación del mensaje de éxito
 */
public class UpdateProfileWappiSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProfileWappiSteps.class);
    private static final String RUTA_BASE_IMAGEN = "src/test/resources/images/";
    private ProfileInformationWappiPage profileInformationWappiPage;
    private MainWappiPage mainWappiPage;
    private GeneralInteraction generalInteraction;


    @Step("se carga la información actualizada del perfil")
    public void updateInformation(ProfileModel profileModel) {
        // Construye la ruta absoluta de la imagen a subir
        final Path rutaImagen = Paths.get(RUTA_BASE_IMAGEN.concat("arnold_profile.jpg")).toAbsolutePath();
        String rutaAbsoluta = rutaImagen.toString();
        
        LOGGER.info("Se registra la información del usuario");
        LOGGER.info("Ruta del archivo:{}", rutaAbsoluta);
        
        // Sube la imagen usando Robot (portapapeles + Ctrl+V + Enter)
        // Esto simula la acción de pegar la ruta en el diálogo de selección de archivo
        generalInteraction.uploadFile(profileInformationWappiPage.inputImage, rutaAbsoluta);
        
        // Llena los campos de texto con los datos del modelo
        profileInformationWappiPage.txtName.type(profileModel.getNombre());
        profileInformationWappiPage.txtLastName.type(profileModel.getApellido());
        profileInformationWappiPage.txtBornDate.type(profileModel.getFechaNacimiento());
        
        // Selecciona el género haciendo clic en el radio button correspondiente
        profileInformationWappiPage.getElementGender(profileModel.getGenero()).click();
        
        // Selecciona el país del dropdown
        profileInformationWappiPage.countryDropdown.selectByVisibleText(profileModel.getPais());
        
        // Hace clic en el botón de guardar para completar la actualización
        profileInformationWappiPage.btnSaveInformationProfile.click();
    }

    @Step("Ir al formulario de actualización de perfil")
    public void goToUpdateProfileForm() {
        // Hace clic en la opción del menú que lleva al formulario de actualización
        mainWappiPage.menuOptionUpdateProfile.waitUntilClickable().click();
        
        // Espera a que el formulario esté visible verificando que el campo nombre aparezca
        // Esto asegura que la navegación fue exitosa antes de continuar
        profileInformationWappiPage.txtName.waitUntilVisible();
    }

    @Step("La actualización se realizó correctamente")
    public void validarActualizacion(List<String> expectedMessages) {
        // Espera hasta 15 segundos a que el mensaje de resultado aparezca
        WebElementFacade mensaje = profileInformationWappiPage.txtUpdateResult
            .withTimeoutOf(Duration.ofSeconds(15))
            .waitUntilVisible();
        
        // Obtiene el texto del mensaje y lo limpia (elimina espacios al inicio/final)
        String texto = mensaje.getText().trim();
        
        // Verifica si el texto del mensaje contiene alguno de los mensajes esperados
        // o si alguno de los mensajes esperados contiene el texto del mensaje
        // Esto hace la validación más flexible ante variaciones en el texto
        boolean coincide = expectedMessages.stream().anyMatch(msg -> 
            texto.contains(msg.trim()) || msg.trim().contains(texto)
        );
        
        // Si no coincide con ninguno de los mensajes esperados, el test falla
        assertThat("La actualización ha fallado. Mensaje: " + texto, coincide, is(true));
    }

}

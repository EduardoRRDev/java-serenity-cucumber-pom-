package co.com.proyecto.automatizacion.pages.interacciones;

import co.com.proyecto.automatizacion.pages.mapeos.MainPage;

/**
 * Clase de interacción específica para la página principal.
 * 
 * Esta clase extiende GeneralInteraction para heredar métodos comunes
 * (scroll, navegación, subida de archivos) y agrega métodos específicos
 * para interactuar con la página principal.
 * 
 * Patrón de separación:
 * - MainPage (mapeos): Contiene los elementos web mapeados
 * - MainPageInteraction (interacciones): Contiene la lógica de interacción
 * 
 * Esto mantiene el código organizado y fácil de mantener.
 */
public class MainPageInteraction extends GeneralInteraction {

    private MainPage mainPage;

    public String getTitleMainPage() {
        // Espera a que el elemento sea visible y luego obtiene su texto
        return mainPage.txtTitleMainPage.waitUntilVisible().getText();
    }

}

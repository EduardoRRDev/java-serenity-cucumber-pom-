# Reliquias Automation – Serenity BDD POM

Proyecto de automatización de pruebas con **Serenity BDD 4.2.0**, **Cucumber 7** (Gherkin) y patrón **Page Object Model (POM)**. Configurado para Java 17, estable y listo para portafolio.

## Stack

| Tecnología   | Versión   |
|-------------|-----------|
| Java        | 17        |
| Serenity BDD| 4.2.0     |
| Cucumber    | 7.18.2    |
| JUnit       | 4.13.2    |
| Gradle      | 8.5       |

## Estructura del proyecto

```
src/
└── test/
    ├── java/co/com/proyecto/automatizacion/
    │   ├── definitions/     # Step definitions (Cucumber)
    │   ├── steps/           # Steps reutilizables (Serenity @Step)
    │   ├── pages/           # Page Objects (mapeos e interacciones)
    │   ├── models/          # Modelos de datos (ej. ProfileModel)
    │   ├── hooks/           # Hooks (DriverHooks)
    │   ├── utilities/       # Utilidades (Robot, etc.)
    │   └── runners/         # Runners por feature
    └── resources/
        ├── features/        # Escenarios Gherkin (.feature)
        ├── serenity.properties
        └── logback-test.xml
```

## Requisitos

- **JDK 17**
- **Chrome** (o configurar otro driver en `serenity.conf`)

## Cómo ejecutar

```bash
# Compilar
./gradlew clean compileJava compileTestJava

# Ejecutar todos los tests y generar reporte Serenity
./gradlew clean test

# Solo login
./gradlew clean test --tests "co.com.proyecto.automatizacion.runners.LoginRunner"

# Solo actualización de perfil
./gradlew clean test --tests "co.com.proyecto.automatizacion.runners.UpdateProfileRunner"
```

Tras `test`, la tarea **aggregate** genera el reporte completo en un directorio con timestamp: **`target/site/serenity-YYYYMMDD-HHmmss`** (index.html, estilos, capturas).

**Cada ejecución crea su propia carpeta**, permitiendo mantener un historial completo de reportes y capturas sin sobrescribir ejecuciones anteriores.

## Dónde ver el reporte y las capturas

1. **Encontrar el reporte más reciente**  
   Al ejecutar los tests, verás en la consola un mensaje como:
   ```
   ==========================================
   Reporte de esta ejecución: C:\...\target\site\serenity-20260217-110645
   ==========================================
   ```
   Los directorios se ordenan por fecha/hora, así que el más reciente es el último.

2. **Abrir el reporte principal**  
   Abre con doble clic (o desde el navegador):  
   **`target\site\serenity-YYYYMMDD-HHmmss\index.html`**  
   *(Abrirlo desde el Explorador de archivos para que las rutas relativas a CSS/imágenes funcionen. Si no ves los cambios tras volver a ejecutar tests, haz **actualización forzada** en el navegador: Ctrl+F5.)*

3. **Ver cada escenario**  
   En la portada del reporte, haz clic en el **nombre del escenario** (por ejemplo "Iniciar sesión exitosamente" o "Completar la actualización básica del perfil") para ir a la página de detalle de ese test.

4. **Desplegar los pasos (y la evidencia)**  
   En la página del escenario, los pasos suelen estar colapsados. Haz clic en la **flecha/caret (▶)** a la izquierda del paso para expandir y ver los subpasos y, si el reporte las enlaza, las capturas.

5. **Ver las capturas directamente**  
   Las capturas se guardan como archivos **`.png`** en la misma carpeta del reporte:  
   **`target\site\serenity-YYYYMMDD-HHmmss\`**  
   Cada ejecución tiene sus propias capturas organizadas por fecha/hora.

6. **Historial de ejecuciones**  
   Todas las ejecuciones anteriores se mantienen en `target\site\` con nombres como:
   - `serenity-20260217-110645` (17 feb 2026, 11:06:45)
   - `serenity-20260217-143022` (17 feb 2026, 14:30:22)
   - etc.

## Configuración

- **Serenity:** `src/test/resources/serenity.properties`  
  (driver, nombre del proyecto, capturas, encoding).
- **Gherkin:** idioma español en los `.feature` (`# language: es`).

## Características

- Patrón **POM** con páginas en `pages/mapeos` e interacciones en `pages/interacciones`.
- **Serenity 4.2.0** con `@Steps`, `@Page`, reportes y capturas en fallos.
- **Cucumber 7** con step definitions en español y glue explícito por definiciones.
- **Java 17** y dependencias alineadas (sin conflictos entre Serenity, Cucumber y JUnit 4).
- **Lombok** en modelos (por ejemplo `ProfileModel`).
- Runners por feature con tags (`@InicioSesionExitoso`, `@ActualizarPerfilBasico`).

## Licencia

Uso interno / portafolio.

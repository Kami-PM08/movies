# Movies-API-RESTful

 Esta API RESTful actúa como intermediaria de la API de The Movie Database (https://www.themoviedb.org/), permitiendo a los usuarios autenticarse y acceder a las funciones para obtener información sobre películas, así como crear y modificar listas.
 
## Requisitos previos

Es necesario que tener instalado y configurado el java versión 11 y Apache Maven.
Conexión estable a internet y acceso a una terminal.

## Configuración

No es necesario hacer una configuración, sin embargo si desea usar su porpia `api_key` de TMDB puede cambiarla puede cambiarla en el archivo `application.yaml`:

```yaml
movies:
  moviesDB:
    apiKey: YourApiKey
    ...
```

## Inicialización

Para empezar a usar la API es necesaria la ejecución del siguiente comando en una terminal ubicada en la raíz del proyecto:

```bash
mvn clean install && mvn clean package
```

Esto para instalar las dependencias y generar la implementación de los mappers que ayudan al procesamiento de datos.

## Ejecución

Para levantar el servidor deberá ejecutar el siguiente comando en la misma terminal:

```bash
mvn spring-boot:run
```

Su servidor se iniciará en la url `http://localhost:8080/`.

Si desea cambiar el puerto puede agregar la variable port al archivo `application.yaml` de la siguente forma:

```yaml
movies:
  ...
server:
  port: YourPort
```

## Importante

Para generar el `sessionId` necesario para el consumo de varios endpoints debe seguir las siguientes instrucciones:

1.  Generar el `token`:

    Consuma el endpoint `http://localhost:8080/api/v1/auth/token`, copie el `token` de la respuesta.

2.  Aprovar el `token`:
    
    Ingrese al link `tokenApprovalUrl` de la respuesta anterior, si tiene una sesión abierta solo debe dar clic en el botón "Aprovar", sino es su caso inicie sesión o cree una sessión en TMDB.

3.  Generar el `sessionId`:

    Consuma el endpoint `http://localhost:8080/api/v1/auth/login` enviando el `token` en la cabecera y copie el `sessionId`.
    
De esta forma ya puede acceder a todas las funciones de esta API RESTful.

Recuerde consumir el endpoint `http://localhost:8080/api/v1/auth/logout` para cerrar su sesión cuando terminé.

## Más

Para más información consulte la documentación en la url http://localhost:8080/swagger-ui/index.html.

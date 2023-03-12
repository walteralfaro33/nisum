# Ejercicio práctico - nisum 1.0

 ## Guia de como levantar la aplicación:
 - Ejecutar la siguientes sentencias en el directorio principal del proyecto "./nisum/"
 * docker-compose up -d --build
 
     Nota A: El comando doker va a ejecutar todas las aplicaciones necesarias para que funcione el ejercicio, para que puedan usarse los export del post adjuntos al proyecto (nisum.postman_collection.json y nisum.token-users.postman_environment.json)
     
     Nota B: El nisum.token-users.postman_environment.json es para cargar el token solo una vez, siempre y cuando no se venza el token.
     
     Nota C: Seguir los pasos de la guia sobre como ejecutar el token:
 
     Nota D: Otra alternativa para usar la APP es levantan el archivo NisumApplication.java 
        
 ## Consideraciones sobre los datos de entrada:
 - Se agrego el ABM completo APIREST del usuario, ya que el enunciado daba a varias interpretaciones.
 - Base de datos H2 en memoria.
 - Autenticación: user-> "admin" y password-> "password" , de manera práctica para el ejercicio se tienen esas credenciales para obtener el token con el jwt. Se podría consultar a una base de datos para tener un ABM de usuario y registrar usuarios nuevos. 
 - SOLID: se implemento "The Clean Architecture", que segun las referencias los principios de SOLID son parte de un conjunto más amplio de principios promovidos por "The Clean Architecture"
 - El versionado de script en flyway, "src/main/resources/db/sql" agregar lo que sea necesario.
 
 ## Tecnologías utilizadas:
 - java 1.11
 - DBH2
 - Flyway (versionado de script)
 - JWT seguridad de apis (token)
 - Spring data jpa
 - Lombok
 - Test: Mockito, Junit
 - ControllerAdvice para el manejo de errores.
 - Swagger
 
 ## Arquitectura
 
 ![Alt text](nisum-arquitectura.png?raw=true "Title")

 ## Coverage: 91% 
 
 NOTA: Todas las apis retornan errores del formato {"mensaje": "mensaje de error"}

 ## GET: Obtener token para usar las apìs de usuario
- Ejemplo: http://localhost:8090/api-nisum/v1/authenticate
  - Body:
 ```json
{
    "username":"admin",
    "password":"password"
}
```
  - Response:
 ```json
{
    "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NDU4ODQxMywiaWF0IjoxNjc0NTcwNDEzfQ.LOLfSgYtmf8x84bxDM_SEzfi-Gx1QQQ3p0b2k3iXWrlq0QqRFZjH1cTTzzH_Wcc16yAiy0v0mc7Q5ICjB_VFNg"
}
```
NOTA: En cada api hay que agregar la autorizacion en "Bearer Token"

 ## GET: Buscar un usuario por id
- Ejemplo: http://localhost:8090/api-nisum/v1/user/1
  - Response:
```json
{
    "idUser": 1,
    "created": "2023-03-12T00:42:53.693082",
    "modified": "2023-03-12T00:43:36.648208",
    "lastLogin": "2023-03-12T00:42:53.693082",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3ODYwOTE3OSwiaWF0IjoxNjc4NTkxMTc5fQ.R4SP0gqp99BzO_pJ-vTv5qU-__a9-QNGK9lk1D3NSmqlcMF4Ex8YP6wC9Q_agXvdsoYSdv_FEEU0jr3EGuAMVw",
    "name": "walter alfaro",
    "mail": "walterka22@gmail.com",
    "password": "aasaAasa8ad5",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "1111",
            "cityCode": "1",
            "countryCode": "57"
        }
    ],
    "active": true
}
```

 ## POST: Crea un nuevo usuario
- Ejemplo: http://localhost:8090/api-nisum/v1/user
   - Body:
 ```json
{
    "name": "Juan Rodriguez",
    "email": "waltera@gmail.com",
    "password": "aasaAasa8ad5",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}
```
  - Response:
```json
{
    "idUser": 1,
    "created": "2023-03-12T00:42:53.693082",
    "modified": "2023-03-12T00:42:53.693109",
    "lastLogin": "2023-03-12T00:42:53.693082",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3ODYwOTE3OSwiaWF0IjoxNjc4NTkxMTc5fQ.R4SP0gqp99BzO_pJ-vTv5qU-__a9-QNGK9lk1D3NSmqlcMF4Ex8YP6wC9Q_agXvdsoYSdv_FEEU0jr3EGuAMVw",
    "active": true
}
 ```

 ## PACHT: Actualizar un usuario
- Ejemplo: http://localhost:8090/api-nisum/v1/user/{id}
   - Body:
 ```json
{
    "name": "walter alfaro",
    "email": "walterka22@gmail.com",
    "password": "aasaAasa8ad5",
    "active": true,
    "phones": [
        {
            "number": "1111",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}
```
   - Response:
```json
{
    "idUser": 1,
    "created": "2023-03-12T00:42:53.693082",
    "modified": "2023-03-12T00:43:36.648208",
    "lastLogin": "2023-03-12T00:42:53.693082",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3ODYwOTE3OSwiaWF0IjoxNjc4NTkxMTc5fQ.R4SP0gqp99BzO_pJ-vTv5qU-__a9-QNGK9lk1D3NSmqlcMF4Ex8YP6wC9Q_agXvdsoYSdv_FEEU0jr3EGuAMVw",
    "name": "walter alfaro",
    "mail": "walterka22@gmail.com",
    "password": "aasaAasa8ad5",
    "active": true
}
```

 ## DELETE: Eliminar a un usuario
- Ejemplo: http://localhost:8090/api-nisum/v1/user/{id}
  - Response: Status 200

 ## Swagger: 
 - URL: http://localhost:8090/swagger-ui.html#/
 
 ## Postman:
 - Exportar el archivo: nisum.postman_collection.json (está en la raíz)
 - Exportar el archivo: nisum.token-users.postman_environment.json (para exportar en el enviroment)
   
 ## Notas:
 - El archivo docker-compose.yaml tiene todas las configuraciones necesarias para que funcione la aplicación
 - Se debe tener instalado previamente docker
 - Otro comando docker:
 * Log: docker-compose logs -f
 * Bajar servidores: docker-compose down
 * Levantar apps(sin instalación previa): docker-compose up
 * Lista de aplicaciones: docker-compose ls
 
 ## Notas 2:
 - Se respeto las convenciones de api rest
 - Tambien se llevo a una arquitectura de codigo "clean architecture" 
 
 ##  Los requerimientos son los siguientes:
    
     Evaluación: JAVA
     
     Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
     Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de
     error.
     Todos los mensajes deben seguir el formato:
     {"mensaje": "mensaje de error"}
     
     Registro
     ● Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña",
     más un listado de objetos "teléfono", respetando el siguiente formato:
     {
         "name": "Juan Rodriguez",
         "email": "juan@rodriguez.org",
         "password": "hunter2",
         "phones": [
                 {
                 "number": "1234567",
                 "citycode": "1",
                 "contrycode": "57"
                 }
         ]
     }
     ● Responder el código de status HTTP adecuado
     ● En caso de éxito, retorne el usuario y los siguientes campos:
     ○ id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería
     más deseable un UUID)
     ○ created: fecha de creación del usuario
     ○ modified: fecha de la última actualización de usuario
     ○ last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la
     fecha de creación)
     ○ token: token de acceso de la API (puede ser UUID o JWT)
     ○ isactive: Indica si el usuario sigue habilitado dentro del sistema.
     ● Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya
     registrado".
     ● El correo debe seguir una expresión regular para validar que formato sea el correcto.
     
     (aaaaaaa@dominio.cl)
     ● La clave debe seguir una expresión regular para validar que formato sea el correcto. (El
     valor de la expresión regular debe ser configurable)
     ● El token deberá ser persistido junto con el usuario
     
     
     Requisitos
     ● Plazo: 2 días, si tienes algún inconveniente con el tiempo comunicate con nosotros
     ● Banco de datos en memoria. Ejemplo: HSQLDB o H2.
     ● Proceso de build vía Gradle o Maven.
     ● Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA.
     ● Framework SpringBoot.
     ● Java 8+
     ● Entrega en un repositorio público (github o bitbucket) con el código fuente y script de
     creación de BD.
     ● Readme explicando cómo probarlo.
     ● Diagrama de la solución.
     Requisitos opcionales
     ● JWT como token
     ● Pruebas unitarias
     ● Swagger

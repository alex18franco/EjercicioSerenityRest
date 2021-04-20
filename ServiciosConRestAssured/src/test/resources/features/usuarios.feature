#language:es
#Author: Alex Alberto Franco Cano
Característica: Manejo de usuarios

  #Registro de usuarios
  Escenario: Registro de usuario exitoso
    Cuando se ingresan los datos del usuario regular a registrar
      |email                   |password  |firstName|lastName|
      |aafranco001@wolox.com.ar|ALEX11cano|Alex     |Franco  |
    Entonces se debe crear el usuario correctamente
    Y ve el codigo de respuesta 201

  Escenario: Registro de usuario no exitoso
    Cuando se ingresan los datos del usuario regular a registrar
      |email      |password  |firstName|lastName|
      |aafranco001|ALEX11    | Alex 121|        |
    Entonces se debe mostrar los errores relacionados con los campos
      |email|Password|firstName|lastName|
    Y ve el codigo de respuesta 422

  #Inicio de sesión de usuarios
  Escenario: Inicio sesión exitoso
    Cuando se inicia sesion con el usuario
      |email                |password  |
      |aafranco@wolox.com.ar|ALEX11cano|
    Entonces el usuario debe ver su id de usuario 854
    Y ve el codigo de respuesta 200
    Y que el codigo de autorizacion no esta vacio

  Escenario: Inicio sesión no contraseña incorrecta
    Cuando se inicia sesion con el usuario
      |email                |password  |
      |aafranco@wolox.com.ar|sinContrasena2|
    Entonces el usuario debe ver el error Unable to authenticate credentials
    Y ve el codigo de respuesta 401

  #Listado de usuarios
  Esquema del escenario: Consultar listado de usuarios según el rol
    Cuando se inicia sesion con el usuario
      |email  |password  |
      |<email>|<password>|
    Y consulta el listado de usuarios
    Entonces el sistema devolvera los usuarios con rol <rol>
    Y ve el codigo de respuesta 200

    Ejemplos:
      |email               |password          |rol       |
      |admin@wolox.com.ar  |candidatoWolox2020|admin     |
      |regular@wolox.com.ar|candidatoWolox2020|regular   |

  #Invalidar sesion de los usuarios
  Escenario: Invalidar sesiones
    Dado se inicia sesion con el usuario
      |email                |password  |
      |aafranco@wolox.com.ar|ALEX11cano|
    Cuando se invalida las sessiones de los usuarios
    Entonces ve el codigo de respuesta 200
#language:es

Característica: Manejo de albumes

  Antecedentes: Iniciar sesión para obtener token
    Cuando se inicia sesion con el usuario
      |email                |password  |
      |aafranco@wolox.com.ar|ALEX11cano|

  #listado de albumes
  Escenario: Consultar listado de albumes
    Cuando el usuario consulta el listado de albumes
    Entonces ve el codigo de respuesta 200

  #listado de fotos de album
  Esquema del escenario: Consultar fotos de un album
    Cuando el usuario consulta el listado de fotos del album <codigo_album>
    Entonces ve el codigo de respuesta 200

    Ejemplos:
      |codigo_album|
      |10          |
      |33          |
      |54          |

  #Comprar Album
  Esquema del escenario: Comprar album
    Dado que el usuario compra el album <id_album>
    Cuando el usuario consulta los albumes comprados por <id_usuario>
    Entonces se evidencia que el album comprado quedo guardado correctamente <id_album>
    Y ve el codigo de respuesta 201

    Ejemplos:
      |id_album|id_usuario|
      | 2     |854       |
      | 3     |854       |

  #Listado de albumes comprados por un usuario
  Esquema del escenario: Consultar los albumes comprados por un usuario
    Cuando el usuario consulta los albumes comprados por <id_usuario>
    Entonces se evidencia que los albumes pertenecen al usuario especificado <id_usuario>
    Y ve el codigo de respuesta 200

    Ejemplos:
      |id_usuario|
      |854       |

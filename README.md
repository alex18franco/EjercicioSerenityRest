# Introduction 
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process
2.	Software dependencies
3.	Latest releases
4.	API references

# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)

-El Readme no posee información suficiente sobre qué se realizó ni cómo ejecutarlo. Es importantísimo contar con la información necesaria en este archivo donde explique requerimientos y uso.
OK--Buena redacción y reutilización de steps en features, en conjunto con un buen uso del background. Se recomienda el uso de tags para agrupar escenarios con una finalidad específica (por ejemplo: casos felices, casos erróneos, etc)
-Los tests no cumplen con las validaciones solicitadas. Por ejemplo, el test “Registro de usuario exitoso” siempre da OK con los mismos datos, lo cual no es real porque al querer registrar el mismo usuario por segunda vez el mismo devuelve un 422.
-No se realizaron validaciones de schema de respuesta.
-No quemar datos de usuario dentro de las features ya que esto no sólo es información sensible para el sistema sino también muy dependiente del entorno donde ejecutas los tests. El día de mañana podrían pedirte que apuntes a otro ambiente los tests y deberías no realizar ningún cambio en el código para esto (sólo en las variables de entorno)
-No utilizar datos fijos para la construcción de los tests si no se aclara o se define que los tests se van a ejecutar siempre con un ambiente en las mismas condiciones. Por ejemplo: para el registro, ese usuario creado funcionará 1 sola vez, a la siguiente ese test fallará. Lo mismo para la compra de álbumes.
Realizar tests más e2e y no tanto unitarios. Por ejemplo, el registro puede finalizar con el correcto login de ese usuario creado. La compra de álbumes debería impedir comprar 2 veces el mismo álbum y debería poder tomar cualquier álbum que me devuelva el endpoint de listado de álbumes. 
-No se recomienda el manejo de lógica ni el instanciamiento de objetos. Cada Step debe hacer un paso específico y no depender de otra información.
-No se debe aplicar groupID / dominio de los package en los módulos de resources ni nivel de test ni de main del arquetipo.
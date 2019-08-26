# Proyecto de Computación Distribuida
----------

El presente proyecto tiene por objetivo solucionar el problema planteado en la clase de computacion distribuida usando Java y RMI. Para ello se tiene lo siguiente: 

> Se desea desarrollar un sistema de adquisición de datos para los diferentes instrumentos de un avión.
> Para ello se pretende realizar un primer prototipo que incluya la adquisición de datos de tres instrumentos: el sistema de navegación inercial, el radioaltímetro y el GPS (véase la figura). El sistema de navegación inercial envía un paquete de datos formado por tres datos de tipo entero y tres de tipo real. El radioaltímetro envía un paquete formado por 10 datos de tipo real. El GPS envía un paquete de datos formado por cinco datos de tipo real y un dato de tipo booleano. Cada instrumento genera un nuevo paquete cada 100 ms.

# RMI
----------
RMI es una API de Java que permite la invocacion remota de metodos. Soporta la transferencia directa de clases Java serializadas. Informacion relacionada con la implementacion de RMI se la puede encontrar [aqui](https://docs.oracle.com/javase/tutorial/rmi/index.html).

# Ejecución
----------
A continuacion se explican los pasos para la ejecucion del proyecto de asignatura (Computacion distribuida), es importante tomar en cuenta que ejecucion se la realizara en base de datos, codigo en CONSOLA y codigo en IDE. 

El script de la base de datos se encuentra en la carpeta del proyecto /proyectoDistribuidas, el script fue ejecutado en MySQL.

Las clases que pueden ser ejecutadas en IDE son las siguientes:

	Paquete "distribuidas"
	-Usuario.java (o la interfaz a ser implementada que usa la clase User.java)
	-InstrumentoGPS.java
	-InstrumentoNInercial.java
	-InstrimentoRadioAltimetro.java

	Paquete "otroServer"
	-ServidorConexionSQL.java

La clase que debe ser ejecutada en CONSOLA es la siguiente:
-Servidor.java

IMPORTANTE: En caso de necesitar usar los instrumentos en otra maquina cambiar la ip directamente desde la clase, para el caso de Usuario.java (o la interfaz a ser implementada que usa la clase User.java) la ip se la instancia desde el constructor de User.java

Orden de ejecucion del programa
- ejecutar "ServidorConexionSQL.java" desde la IDE
- ejecutar "Servidor.java" desde consola con el siguiente comando:
 ```
java -Djava.rmi.server.hostname=IpDeLaMaquina -Djava.security.policy=server.policy distribuidas.Servidor
 ```
Reemplazar IpDeLaMaquina por la IP de la maquina (-.-), es importante notar que el comando se lo ejecuta fuera del paquete, es decir "proyectoDistribuidas/Distribuidas/src", ademas las clases Server.java e Interfaz.java deben ser previamente compiladas con el comando "javac".
- ejecutar los instrumentos desde la IDE
- ejecutar el usuario

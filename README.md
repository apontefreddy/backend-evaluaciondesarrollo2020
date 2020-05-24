# TestDesarrollo
===============
req: maven 3 , jdk 8
=============
PASOS:
1. clonar repo enviado en el email.

2. Se puede montar en eclipse. 
o hacer un "mvn clean install", posicionandose en la raiz del proyecto
luego correr el jar ubicado en la carpeta target (posicionarse en ella), y ejecutando:
java -jar TestDesarrollo-0.0.1-SNAPSHOT.jar 

------------------
Pueden consumir el servicio atraves de este
endpoint: http://127.0.0.1:8080/api/persona
metodo:POST
Ejemplo de json a enviar:
{
	"nombre":"Maria",
	"apellido":"Gonzalez",
	"fechaNacString":"1990-05-23"
}

y consultar las personas ingresadas a traves de este
endpoint: http://127.0.0.1:8080/api/listapersonas
metodo:GET
-------------------
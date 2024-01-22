# Utiliza una imagen base de OpenJDK 17
FROM openjdk:17

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/tu-aplicacion.jar .

# Comando por defecto para ejecutar la aplicación
CMD ["java", "-jar", "tu-aplicacion.jar"]

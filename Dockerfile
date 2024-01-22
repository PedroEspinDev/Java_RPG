# Usa la imagen base oficial de OpenJDK con Java 17
FROM adoptopenjdk:17-jdk-hotspot

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml al directorio de trabajo
COPY pom.xml .

# Copia los archivos de código fuente al directorio de trabajo
COPY src ./src

# Empaqueta la aplicación usando Maven
RUN ["mvn", "package", "--batch-mode", "--update-snapshots"]

# Expone el puerto en el que se ejecutará la aplicación (ajústalo según tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicia
CMD ["java", "-jar", "target/tu-artifact-id-tu-version.jar"]

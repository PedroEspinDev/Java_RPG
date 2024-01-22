# Usa la imagen base oficial de OpenJDK con Java 17
FROM openjdk:17-jdk

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml al directorio de trabajo
COPY pom.xml .

# Copia los archivos fuente y recursos al directorio de trabajo
COPY src ./src

# Empaqueta la aplicación utilizando Maven
RUN ./mvnw package -DskipTests

# Expone el puerto si la aplicación lo requiere
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor se inicia
CMD ["java", "-jar", "target/JavaRPG.jar"]

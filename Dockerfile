# Используем официальный образ OpenJDK 17
FROM openjdk:21

# Указываем рабочую директорию
WORKDIR /app

# Копируем скомпилированный JAR-файл
COPY target/*.jar app.jar

# Открываем порт 8080
EXPOSE 8080

# Запускаем Spring Boot приложение
ENTRYPOINT ["java", "-jar", "app.jar"]

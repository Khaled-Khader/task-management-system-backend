FROM eclipse-temurin:21-jre

WORKDIR /app

COPY task-management-system-presentation/task-management-system-presentation-spring-boot/task-management-system-presentation-spring-boot-main/target/task-management-system-presentation-spring-boot-main-1.0-SNAPSHOT.jar app.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]

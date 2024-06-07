FROM openjdk:17
COPY "./target/empleados-asistencia-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 6563
ENTRYPOINT [ "java", "-jar", "app.jar"  ]
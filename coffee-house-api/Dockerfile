FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /api
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests=true

FROM eclipse-temurin:17-jre-jammy
WORKDIR /api
EXPOSE 8085
COPY --from=builder /api/target/*.jar /api/*.jar
ENTRYPOINT ["java", "-jar", "/api/*.jar" ]
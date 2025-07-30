FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine@sha256:b6ab039066382d39cfc843914ef1fc624aa60e2a16ede433509ccadd6d995b1f
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser


# Defina a variável de ambiente para o profile desejado

# Defina a variável de ambiente para a conexao com base de dados postgres
# ARG DRIVE_CLASS
# ARG DB_IP
ENV SPRING_DB_NAME=sigo
ENV SPRING_DB_USER=user_tre
ENV SPRING_DB_PASS=OV@sVIu*
ENV SPRING_DB_HOST=10.72.209.22
ENV SPRING_DB_PORT=5432
ENV SPRING_PROFILE=dev

# Define a variavel de ambiente para username e password do basic authentication
ENV SPRING_ACCESS_USERNAME=sigo
ENV SPRING_ACCESS_PASSWORD=sigo
ENV SPRING_ACCESS_HOST_SWAGGER=http://localhost:8080/api/swagger-ui/index.html

COPY --from=build /project/target/msoccurrence.jar /app/msoccurrence.jar
WORKDIR /app
#ARG profile=dev

EXPOSE 8080

RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-Dspring.profiles.active=${SPRING_PROFILE}" "-jar" "msoccurrence.jar"

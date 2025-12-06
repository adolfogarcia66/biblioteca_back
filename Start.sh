#!/bin/bash

# Compila la app
./mvnw clean package

# Arranca el JAR generado
java -jar target/bliblioteca-0.0.1-SNAPSHOT.jar

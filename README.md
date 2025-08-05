# 🧩 Projet Microservices - Spring Boot + Eureka + Swagger

Ce projet implémente une architecture microservices avec **3 composants principaux** :
1. `eureka-server` : service de découverte 
2. `sector-service` : service de gestion des secteurs
3. `classes-service` : service de gestion des classes 

Tous les microservices sont **enregistrés dynamiquement** dans le serveur Eureka et exposent une documentation **Swagger** pour faciliter les tests.



#  Structure du projet

examen-microservices/

├── eureka-server/ --> Service Eureka

├── sector-service/ --> Microservice de gestion des secteurs

├── classes-service/ --> Microservice de gestion des classes



# Technologies utilisées

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Cloud Eureka Server / Client
- Swagger (Springdoc OpenAPI 3)
- MySQL
- Maven


# Configuration de base

# Eureka Server (`eureka-server`)
- Contient une classe `EurekaServerApplication.java`
- Activé avec `@EnableEurekaServer`
- `application.yml` :
```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
Lancement : http://localhost:8761/
→ Affiche la liste des microservices enregistrés.

 Sector Service (sector-service)
 Entité principale

@Entity
public class Sector {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
```

# Classes Java principales

Classe	Description

Sector	Entité JPA représentant un secteur
SectorRepository	Interface JPA pour accéder aux données
SectorService	Classe métier (injection de SectorRepository)
SectorController	API REST (CRUD) 
application.properties	Contient les infos Eureka + MySQL

# les endpoints Swagger :

Accès via : http://localhost:8081/swagger-ui.html

GET /api/sectors

POST /api/sectors

PUT /api/sectors/{id}

DELETE /api/sectors/{id}

 Classes Service (classes-service)
 Entité principale

@Entity
public class Classe {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Sector sector; 
}

# Classes Java principales

Classe	Description
Classe	Entité JPA
ClasseRepository	Interface JPA
ClasseService	Service métier
ClasseController	API REST (CRUD)
application.properties	Config Eureka + MySQL

# Les endpoints Swagger :

Accès via : http://localhost:8082/swagger-ui.html

GET /api/classes

POST /api/classes

PUT /api/classes/{id}

DELETE /api/classes/{id}


# Tester l’application
1. Lancer Eureka

cd eureka-server
mvn spring-boot:run
 Accessible via :http://localhost:8761
2. Lancer Sector Service

cd ../sector-service
mvn spring-boot:run
 Accessible : http://localhost:8081/swagger-ui.html
3. Lancer Classes Service

cd ../classes-service
mvn spring-boot:run
 Accessible : http://localhost:8082/swagger-ui.html


# Le script de la base de données
Chaque microservice a sa propre base de donnée

CREATE DATABASE sector_db;
CREATE DATABASE Classe_db;

-- Table Sector
CREATE TABLE sector (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100)
);

-- Table Classe
CREATE TABLE classe (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  sector_id BIGINT,
  FOREIGN KEY (sector_id) REFERENCES sector(id)
);
# Swagger UI
Swagger est déjà intégré via Springdoc OpenAPI !

http://localhost:8082/swagger-ui.html → Classes

http://localhost:8081/swagger-ui.html → Sectors

# Dépendances Maven importantes
(pom.xml)

<dependencies>
  <!-- Spring Boot & Web -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <!-- JPA -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>

  <!-- Eureka Client -->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>

  <!-- Swagger OpenAPI -->
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.7.0</version>
  </dependency>

  <!-- MySQL -->
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
  </dependency>
</dependencies>

🧑‍💻 Auteur
Ce projet a été développé par Aminata Gueye
Master 1 - Génie Logiciel Groupe 1 – ISI

# SQUELETTE APPLI CREE POUR FILR4MINIP 2019P

## CONTEXTE
 - http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=FilRouge.AppliExemplaireResa : domaine d'application à cette appli
 - https://github.com/umlp/squelette2019 : synthèse techno IHM (SpringBoot + Thymeleaf) + persistance (JDBC)
 - https://github.com/prodageo/jdbc4uemf : mise en oeuvre persistance (JDBC) only

## A FAIRE
 - [ ] exploiter fragments Thymeleaf pour ajouter menus
 - [ ] mock ResultSet (https://github.com/mcrisc/mock-resultset)
   - https://www.javacodegeeks.com/2018/09/java-mocking-resultset-using-mockito.html
   - https://www.baeldung.com/java-convert-reader-to-string : StringReader("cvs like text") en place de InputStreamReader
 - [ ] intégrer fct de prodageo/jdbc4uemf (cf pom.xml)
 - [ ] encoding dans fichier application.properties

# Archives
## projet source : Spring Boot Hello World Example with Thymeleaf

## Guide
https://hellokoding.com/spring-boot-hello-world-example-with-thymeleaf/

## What you'll need
- JDK 1.7 or later
- Maven 3 or later

## Stack
- Spring Boot
- Java

## Run
`mvn spring-boot:run`

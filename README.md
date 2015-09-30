# Project to generate a graphic tag from form parameters

##Installation
In order to run this project:

###Install barcode library in your maven local repository

* mvn install:install-file -Dfile=<project-dir>/lib/barcode.jar -DgroupId=br.org.roger.barcode  -DartifactId=barcode -Dversion=1.0.0 -Dpackaging=jar

###Run from command line

* mvn spring-boot:run

###Access the application

* http://localhost:8080/pages/form.html

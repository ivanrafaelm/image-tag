# image-tag
Project to generate a graphic tag from form parameters

Installation
In order to run this project:

1. Install barcode library in your maven local repository

mvn install:install-file -Dfile=<project-dir>/lib/barcode.jar -DgroupId=br.org.roger.barcode -DartifactId=barcode -Dversion=1.0.0 -Dpackaging=jar

2. Run from command line

mvn spring-boot:run

# DBsync.Java :coffee: # 

Das ist ein Tool mit den man zwei **Datenbanken** vergleicht und welche dann über einen logger Differenzen meldet. <br>

# Mit diesen Werkzeugen wurde das Projekt umgesetzt: #

**IDE: Visual Studio Code mit den Extensions Git Graph, Extensions Pack for Java (6 in 1), Live Share. <br>
PostgreSQL mit zwei Datenbänken mit jeweils gleichen Datensätzen <br>
Java version JDK             <br>
Spring Boot** <br>

## Voraussetzungen für das anwenden des DBsync-Tool ##

<sub> Bitte ihre Login Daten in die **"Properties-Datei"** für die Datenbank in PgAdmin Panel eingeben: <sub> <br>

**username=               <br>
password=                 <br>
connector_db=             <br>
portal_db=                <br>
connector_tabelle=        <br>
portal_vergleichstabelle=**

JDK 19 Install-Link anklicken und JDK 19 installer anklicken:
https://www.oracle.com/java/technologies/downloads/#jdk19-windows



Git Download-Link:
https://git-scm.com/download/win

## Das Programm kompilieren

Um das Programm **"DBsync "** zu kompilieren, geben Sie folgendes ein::

```console
javac -d .\target\classes\ -cp "C:\Users\BENUTZERNAME\.m2\repository\org\postgresql\postgresql\42.5.0\postgresql-42.5.0.jar;C:\Users\BENUTZERNAME\.m2\repository\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar" .\src\main\java\de\praktikant\*.java
```
:exclamation: Bitte **BENUTZERNAME** durch eigenen Benutzernamen ersetzen.

## Das Programm ausführen

Um das Programm zu starten, müssen Sie folgendes eingeben:

```console
java -cp ".\target\classes\;C:\Users\BENUTZERNAME\.m2\repository\org\postgresql\postgresql\42.5.0\postgresql-42.5.0.jar;C:\Users\BENUTZERNAME\.m2\repository\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;C:\Users\BENUTZERNAME\.m2\repository\org\slf4j\slf4j-simple\1.7.30\slf4j-simple-1.7.30.jar" de.praktikant.Start

```
:exclamation: Bitte **BENUTZERNAME** durch eigenen Benutzernamen ersetzen.

## Quellen, die für das erstellen der Anwendungen hilfreich waren ##

  https://jdbc.postgresql.org/
  
  https://www.postgresql.org/
  
  https://www.slf4j.org/manual.html
  
  https://sematext.com/blog/slf4j-tutorial/
  
  https://hellocoding.de/blog/coding-language/java/strings-formatieren
  
  https://www.atlassian.com/de/git/tutorials/saving-changes/gitignore
  
  https://www.javatpoint.com/java-logger

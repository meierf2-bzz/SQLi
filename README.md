# SQL Injection Demo

Dieses Projekt demonstriert verschiedene Ansätze zur Implementierung von Login-Funktionen in einer Spring-Boot-Anwendung und zeigt dabei die Sicherheitsrisiken von SQL-Injection auf.

## Projektübersicht

Das Projekt enthält drei verschiedene Controller, die jeweils unterschiedliche Methoden zur Authentifizierung von Benutzern implementieren:

1. **`UserControllerNot`**: Unsicherer Ansatz, der anfällig für SQL-Injection ist, da SQL-Abfragen direkt mit Benutzereingaben erstellt werden.
2. **`UserController`**: Sicherer Ansatz, der vorbereitete SQL-Statements (`PreparedStatement`) verwendet, um SQL-Injection zu verhindern.
3. **`UserControllerJPA`**: Verwendung von Spring Data JPA, um SQL-Abfragen sicher und effizient zu handhaben.

## Technologien

- **Programmiersprache**: Java
- **Framework**: Spring Boot
- **Build-Tool**: Maven
- **Datenbank**: SQL

## Projektstruktur

- `UserControllerNot`: Unsicherer Controller, der SQL-Injection demonstriert.
- `UserController`: Sicherer Controller mit `PreparedStatement`.
- `UserControllerJPA`: Sicherer Controller mit Spring Data JPA.
- `UserRepository`: JPA-Repository für Datenbankoperationen.
- `User`: Entitätsklasse für Benutzer.

## Sicherheitsaspekte

- **SQL-Injection**:
    - `UserControllerNot` zeigt, wie SQL-Injection durch unsichere Abfragen ausgenutzt werden kann.
    - `UserController` und `UserControllerJPA` verwenden sichere Methoden, um SQL-Injection zu verhindern.
- **Best Practices**:
    - Verwenden Sie immer vorbereitete Statements oder ORM-Frameworks wie JPA.
    - Validieren und bereinigen Sie Benutzereingaben.

## Endpunkte

### Unsicherer Controller (`/unsafe`)
- **GET** `/unsafe/login`: Login mit unsicheren SQL-Abfragen.

### Sicherer Controller (`/safe`)
- **GET** `/safe/login`: Login mit `PreparedStatement`.

### JPA-Controller (`/jpa`)
- **GET** `/jpa/login`: Login mit Spring Data JPA.

## Nutzung

1. Starten Sie die Anwendung mit `mvn spring-boot:run`.
2. Greifen Sie auf die verschiedenen Endpunkte zu, um die Unterschiede in der Sicherheit zu testen.
3. Testen Sie SQL-Injection mit Eingaben wie:
    - Benutzername: `' OR '1'='1`
    - Passwort: `' OR '1'='1`

## Lizenz

Dieses Projekt dient ausschließlich zu Bildungszwecken. Verwenden Sie die hier gezeigten unsicheren Praktiken nicht in Produktionsanwendungen.
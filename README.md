## Modul 165 Projekt - League of Legends Quiz

Dieses Projekt wurde im Rahmen des Moduls 165 an der Berufsbildungsschule Winterthur (bbw) erstellt. Es handelt sich um ein Quiz über "League of Legends", ein bekanntes Online-Multiplayer-Spiel.

### Ziel des Projekts
Das Hauptziel dieses Projekts ist die Erstellung eines interaktiven Quiz, das Wissen über Charaktere aus "League of Legends" abfragt. Spieler können Fragen zu verschiedenen Eigenschaften wie Lebenspunkte, Angriff und Verteidigung beantworten.

### Umsetzung
- Verwendete Technologien: Spring Boot, Java, MongoDB, Angular.
- Datenbank: Die Charakterdaten werden in einer MongoDB-Datenbank gespeichert.
- Quizgenerator: Es gibt Funktionen zum Erstellen von Quizfragen basierend auf verschiedenen Charaktereigenschaften.

### Anwendung starten
1. Stelle sicher, dass Docker und MongoDB Compass installiert sind.
2. Starte Docker.
3. Starte MongoDB mit den erforderlichen Konfigurationen.
4. Navigiere zum `frontend`-Verzeichnis und starte die Angular-Anwendung mit: `ng serve`
5. Starte die Spring Boot-Anwendung im Projektverzeichnis mit: `./mvnw spring-boot:run`
6. Die Anwendung ist unter `http://localhost:4200` verfügbar.

### Datenbankverbindung
- Host: `localhost`
- Port: `27017`
- Datenbankname: `league_quiz`

### Autor
Yanis Meichtry
(14.12.2023)

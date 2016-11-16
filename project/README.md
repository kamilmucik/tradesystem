# Java Template Junior+ 2016


Aby uruchomic szablon potrzebne jest:

* java w wesji 8
* maven


Aby uruchomić aplikacje należy w katalogu middle-tier użyc polecenia:
---
`mvn spring-boot:run`

Program można uruchomić też używając dowolnego IDE i uruchamiając klase com.gft.Configuration

Program uruchamia sie na domyślnym porcie 8090, aby zmienić port należy zmienić linie:

`server.port = 8090`

w pliku `application.properties`


-----------------
Użyte technologie i opis funkcjonalności:

* Java 8

* Spring boot

* prosty CRUD (spring data)

* wystawione pare endpointów REST’owych

* skonfigurowany i wystawiony websocket

* do budowania uzyty maven z podziałem na moduły




Opis modułów(moduły zostały storzone na podstawie schematu https://agema.gft.com/confluence/display/~srsk/Program+description):

* backend - moduł służący do implementacji logiki biznesowej i polaczniu z baza danych

* middle-tier - modul slużący do komunikacji pomiędzy backend'em a frontendem

* messages - wspólny moduł dla wiadomości które będa wysyłane pomiędzy modułami backend i middle-tier. Ten moduł jest już dodany do modułów middle-tier i backend.

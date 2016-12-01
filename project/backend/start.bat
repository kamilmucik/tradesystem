call "mvn spring-boot:run -Drun.arguments=--server.port=9090 -Dinstance.number=231" inject

mvn
spring-boot:run
-Drun.arguments=--server.port=9090
-Dinstance.number=231
-Dbroker.url=tcp://localhost:61617
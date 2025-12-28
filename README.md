# myFitBuddy
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Spring_Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

## Lancer le projet en prod

1. Modifier la configuration application.yml dans le dossier src/main/resources

```sh
spring.profiles.active=prod
```



2. Lancer l'API

```sh
mvn spring-boot:run
```

## Lancer le projet en local

1. Lancer la BDD dockerisée

```sh
#Sur windows : activer le docker desktop 
docker-compose up -d
```

2. Modifier la configuration application.yml dans le dossier src/main/resources

```sh
spring.profiles.active=local
```

3. Lancer l'API

```sh
mvn spring-boot:run
```

4. Accéder à Swagger UI

`http://localhost:8080/swagger-ui/index.html` (Adapter le port si nécessaire).

Il est possible de s'authentifier avec les identifiants `dbuser:dbuser` ou `admin:admin`.

4. Penser à desactiver Docker après utilisation

```sh
docker-compose down
```

## Prettier

Pour vérifier le formattage du code : 

```sh
mvn prettier:check
``` 

Pour réecrire le formattage : 

```sh
mvn prettier:write
``` 

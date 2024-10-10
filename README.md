# myFitBuddy

## Lancer le projet en local

1. Lancer la BDD dockerisée

```sh
#Sur windows : activer le docker desktop 
docker-compose up
```

2. Lancer l'API

```sh
mvn spring-boot:run
```

3. Accéder à Swagger UI

`http://localhost:8080/swagger-ui/index.html` (Adapter le port si nécessaire).

4. Penser à desactiver Docker après utilisation

```sh
docker-compose down
```
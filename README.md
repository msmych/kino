# Kino

Movies library app

### How to run

```shell
./gradlew app:runFatJar
```

With server running, try making requests:
```shell
curl -X POST -H "Content-Type: application/json" -d '{"title": "Barbie", "year": 2023}' localhost:8080/movies
curl localhost:8080/movies/<id>
```

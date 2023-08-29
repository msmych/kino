# Kino

Movies library app

### How to run

Run a Postgres DB:
```shell
docker run --name kino-pg \
  -e POSTGRES_DB=postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 55000:5432 \
  -d postgres
```

With Postgres running, apply Flyway migrations:
```shell
./gradlew flywayMigrate \
  -Pkino.flyway.url=jdbc:postgresql://localhost:55000/postgres \
  -Pkino.flyway.user=postgres \
  -Pkino.flyway.password=postgres
```

Then run the app:
```shell
./gradlew app:runFatJar
```

With server running, try making requests:
```shell
# create movie with given title and year
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"title": "Barbie", "year": 2023}' \
  localhost:8000/api/movies
# returns created movie id

# update movie title
curl -X PATCH \
  -H "Content-Type: application/json" \
  -d '{"title": "Oppenheimer"}' \
  localhost:8000/api/movies/<id>

# get movie by id
curl localhost:8000/api/movies/<id>
```

Build Docker image and publish to local registry:
```shell
./gradlew app:buildImage
./gradlew app:publishImageToLocalRegistry
```

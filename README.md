# Kino

Movies library app

### How to run

Run a Postgres DB, for example:
```shell
docker run --name kino-pg \
  -e POSTGRES_DB=<db-name> \
  -e POSTGRES_USER=<user> \
  -e POSTGRES_PASSWORD=<password> \
  -p <db-port>:5432 \
  -d postgres
```

With running Postgres, run Flyway migrations:
```shell
./gradlew flywayMigrate \
  -Pkino.flyway.url=<jdbc-url> \
  -Pkino.flyway.user=<user> \
  -Pkino.flyway.password=<password>
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
  localhost:8000/movies
# returns created movie id

# get movie by id
curl localhost:8000/movies/<id>
```

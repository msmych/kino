# Kino

Movies library app

### How to run

Run a Postgres DB, for example:
```shell
docker run --name kino-pg \
  -e POSTGRES_DB=postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 55000:5432 \
  -d postgres
```

With running Postgres, run the following SQL script:
```postgresql
create schema if not exists kino;

create table if not exists kino.movies
(
    id    uuid   not null primary key,
    title text   not null,
    year  bigint not null
);
```

Then run the app:
```shell
./gradlew app:runFatJar
```

With server running, try making requests:
```shell
curl -X POST -H "Content-Type: application/json" -d '{"title": "Barbie", "year": 2023}' localhost:8080/movies
curl localhost:8080/movies/<id>
```

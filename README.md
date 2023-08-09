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

With Postgres running, run the following SQL script:
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
# create movie with given title and year
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"title": "Barbie", "year": 2023}' \
  localhost:8000/api/movies
# returns created movie id

# get movie by id
curl localhost:8000/api/movies/<id>
```

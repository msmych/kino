# Kino

Movies library app

### How to run

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

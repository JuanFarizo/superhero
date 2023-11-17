
# Superhero

CRUD for Superheros




## Installation

Install demo app with mvn & docker

```bash
  mvn clean install -f ./pom.xml
  docker build -t farins/superherodemoapp:latest -f ./Dockerfile ./
  docker-compose up -d
```


## API Reference

#### Get all superheros

```http
  GET /api/superhero/
```


#### Get superhero by id

```http
  GET /api/superhero/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of superhero to fetch |

#### Get superheros by name

```http
  GET /api/superhero/name/${name}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. Name of superhero to fetch |


#### Create superhero

```http
  POST /api/superhero/
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. Name of superhero |

``` json
{
    "name": "example"
}
```

#### Update superhero

```http
  PATCH /api/superhero/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of superhero to fetch |

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. Name of superhero |

``` json
{
    "name": "example"
}
```

#### Delete superhero by id

```http
  DELETE /api/superhero/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of superhero to fetch |


## Tech Stack

**Server:** Spring, Mvn

**Database:** H2, Migration: Flyway





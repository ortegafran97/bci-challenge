# BCI-CHALLENGE

## Description

This repository was developed as a technical challenge. It is working for creation of users.


## API usage

For testing the project, once started, we have to use our preferred for API testing (i.e. Postman) and set the HTTP Method we need to test.

The default API route and port is `http://localhost:8080`

### Endpoints

#### GET all users
> GET http://localhost:8080/user/

Lists all the users registered



#### GET user by id
> GET http://localhost:8080/user/988d50da-e3f6-43f6-bdff-b46c5a412b45

Response
```json
{
    "id": "988d50da-e3f6-43f6-bdff-b46c5a412b45",
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "createdAt": "2024-02-29T13:25:20.391+00:00",
    "updatedAt": "2024-02-29T13:25:20.391+00:00",
    "lastLogin": "2024-02-29T13:25:20.392+00:00",
    "phones": [
        {
            "id": "8913b44b-37ea-45d1-8e4f-d71df5963022",
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```

#### POST User

> POST http://localhost:8080/user

Request Body
```json
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
        }
    ]
}
```

Response
```json
{
    "id": "300c1224-c5f6-4e49-b933-cb06870ddd5b",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOiIzMDBjMTIyNC1jNWY2LTRlNDktYjkzMy1jYjA2ODcwZGRkNWIiLCJ1c2VybmFtZSI6Ikp1YW4gUm9kcmlndWV6IiwiZW1haWwiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MDkyMTI5OTEsImV4cCI6MTcxMDA3Njk5MX0.SD8Ip9HnfS575Psky4fZn55_0OG4hFEya_WBGNMQ7uI",
    "createdAt": "2024-02-29T10:23:10.9179426",
    "updatedAt": "2024-02-29T10:23:10.9179426",
    "lastLogin": null,
    "active": false
}
```
#### DELETE User
> DELETE http://localhost:8080/user/988d50da-e3f6-43f6-bdff-b46c5a412b45

Response
```
    true
```

---

### TO DO TASKS

* Fix GET user by id: avoid returning password

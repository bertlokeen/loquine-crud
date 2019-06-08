# Basic Java Spring Boot REST API
Initialized with Web, JPA, Actuator, DevTools, and H2 in-memory database.

## Basic Project
A author and book basic crud example with relationship, exception jandling and validation.

### Sample APIs
Full api documentation here (Follow this Link)[https://www.getpostman.com/collections/a5effd5180a819ad11f1]
#### Get all authors
**Request**
```https
GET /api/v1/authors
```
**Response**
```javascript
{
  "message" : string,
  "success" : bool,
  "data"    : string
}
```

#### Get an author
**URL Parameters**
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `id` | `int` | author id |
**Request**
```https
GET /api/v1/authors/{id}
```
**Response**
```javascript
{
  "message" : string,
  "success" : bool,
  "data"    : string
}
```
#### Add an author
**Request Body**
```javascript
{
  "name" : "John Doe",
  "contact_number" : "+639123456789",
  "date_of_birth" : "1990-06-20"
}
```
**Request**
```https
POST /api/v1/authors
```
**Response**
```javascript
{
    "id": 1,
    "name": "John Doe",
    "contact_number": "+639123456789",
    "date_of_birth": "1990-06-20T00:00:00.000+0000",
    "books": []
}
```


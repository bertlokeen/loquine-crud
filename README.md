# Basic Java Spring Boot REST API
Initialized with Web, JPA, Actuator, DevTools, and H2 in-memory database.

## Basic Project
A author and book basic crud example with relationship, exception jandling and validation.

### Sample APIs
Full api documentation here [https://www.getpostman.com/collections/a5effd5180a819ad11f1]
#### Get all authors
**Request**
```https
GET /api/v1/authors
```
**Response**
HTTP Status 200 `OK`
```javascript
{
  "id": 1,
  "name" : "John Doe",
  "contact_number" : "+639123456789",
  "date_of_birth" : "1990-06-20"
}
```

#### Get an author

**URL Parameters**

**Request**
Author ID `id` `int`

```https
GET /api/v1/authors/{id}
```
**Response**
HTTP Status 201 `CREATED`
```javascript
{
  "id": 1,
  "name" : "John Doe",
  "contact_number" : "+639123456789",
  "date_of_birth" : "1990-06-20"
}
```
#### Add an author
**Request Body**
```javascript
{
  "name" : "John Doe",
  "contact_number" : "+639123456789",
  "date_of_birth" : "1990-06-20",
  "books": []
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


# Managing Users Exercise

This is a Spring Boot project for creating and consulting users.

## Installation

### Prerrequisites
- Java 11 JDK
- Gradle 7.4

For building the application, please execute this command:

```bash
gradle build
```
For executing the project, use this command:

```bash
gradle :bootRun
```

## Usage
You can use Postman for checking the endpoints. This project has two, so you can do some tests:
- http://localhost:8080/sign-up . The following json structure is an example for using this endpoint:

### Request example:
```json
{
  "name": "Tom Wright",
  "email": "tom.wright@domain.com",
  "password": "a2asfGfdfdf4",
  "phones": [
    {
      "number": 123456,
      "cityCode": 20,
      "countryCode": "112"
    }
  ]
}
```
### Response example:

```json
{
    "id": "dfa6df66-0ab9-4648-b79f-73ebaf22a6a5",
    "created": "2025-11-11T19:15:08.507+00:00",
    "lastLogin": "2025-11-11T19:15:08.507+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20ud3JpZ2h0QGRvbWFpbi5jb20iLCJ1c2VySWQiOiJkZmE2ZGY2Ni0wYWI5LTQ2NDgtYjc5Zi03M2ViYWYyMmE2YTUiLCJpYXQiOjE3NjI4ODg1MDgsImV4cCI6MTc2Mjk3NDkwOH0.4byKLWNFmqvHztMhG5NGGsIOQb5XE9HmZGxR3XaeyYs",
    "isActive": true
}
```

- http://localhost:8080/login . This endpoint needs a token as a request parameter. This token is coming from the sing-up endpoint, in the token field.

### Response example:
```json
{
    "id": "dfa6df66-0ab9-4648-b79f-73ebaf22a6a5",
    "created": "2025-11-11T19:15:08.507+00:00",
    "lastLogin": "2025-11-11T19:15:08.507+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20ud3JpZ2h0QGRvbWFpbi5jb20iLCJ1c2VySWQiOiJkZmE2ZGY2Ni0wYWI5LTQ2NDgtYjc5Zi03M2ViYWYyMmE2YTUiLCJpYXQiOjE3NjI4ODg1NzQsImV4cCI6MTc2Mjk3NDk3NH0.-bxm9FC-G0g7k7leGxLeibI-SPa96UpYSeN05Xi7w0w",
    "isActive": true,
    "name": "Tom Wright",
    "email": "tom.wright@domain.com",
    "password": "b95SBW2x2h6qrY1u7v+WSnhO8xmM1Wq0QKxRj0VLJ6Gk5qLaBeqIriU8A8uI/P/Sshf0QOOljTGlLwv8ESLb2HdKJdnJPBDOL7gVQo47Mf5I1MO1KR3y3F9AlzEQOej/NZvMDfM7AiiHsZdGXezUmouHYn3MgvEqHbL08DJA2VCG8W5O2PN+Z0PgXZYFT9HI7a98kmNcP+YWudpjdA8zUAX+9fDtkTimdvd8A5ZqIYCQrs/hueKF7of6wD1y6gjydexTpCRJvtDF11rUgRbQj+xunmA55YuiEK3ef8YFr17RTB1j72h8IDP9u4TCaQB69qOsodu4BPx6/Velc6kIKA==",
    "phones": [
        {
            "number": 123456,
            "cityCode": 20,
            "countryCode": "112"
        }
    ]
}
```


## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

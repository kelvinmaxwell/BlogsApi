# API Documentation

## Running the App

Applications.yml:
- Specified the port to run on a specific port, which can be changed as needed.
- The database configuration also needs to be changed as the environment changes.
- `ddl-auto` set to update to always make changes to the database as specified on the model classes.
- Signing key is used to generate JWT auth tokens.
- Config files contain descriptions for mainly configuring API responses.

## Getting Started

To get started:
1. Use the signup endpoint to register a new user as specified in the endpoint section.
2. Use the new details to generate an auth token used to access other protected endpoints as a bearer token.
3. Delete operations are only limited to the ADMIN role. Other endpoints are open to all user roles.

## Endpoints

sample end points and responsestructure( Comments
)
#### Get all Comment by comments

- **Method:** GET
- **Endpoint:** `localhost:8085/api/v1/comments`
- **Request Body:** None
- **Sample Response Body:**
  ```json
  {
      "status_code": 200,
      "status_type": 1,
      "status_desc": "Success",
      "results_object":[ {
          "id": 1,
          "content": "Sample comment content",
          "post_id": 2,
          "username": "sample_user",
          "created_at": "2024-03-17T10:00:00Z"
      }]
  }
#### Get a Comment by ID

- **Method:** GET
- **Endpoint:** `localhost:8085/api/v1/comments/{comment_id}`
- **Request Body:** None
- **Sample Response Body:**
  ```json
  {
      "status_code": 200,
      "status_type": 1,
      "status_desc": "Success",
      "results_object": {
          "id": 1,
          "content": "Sample comment content",
          "post_id": 2,
          "username": "sample_user",
          "created_at": "2024-03-17T10:00:00Z"
      }
  }

#### Post a Comment 

- **Method:** POST
- **Endpoint:** `localhost:8085/api/v1/comments`
- **Request Body:**
  ```json
  {
          "content": "Sample comment content",
          "post_id": 2,
          "username": "sample_user",
          "created_at": "2024-03-17T10:00:00Z"
      }
- **Sample Response Body:**
  ```json
  {
      "status_code": 200,
      "status_type": 1,
      "status_desc": "Success",
      "results_object": {
          "id": 1,
          "content": "Sample comment content",
          "post_id": 2,
          "username": "sample_user",
          "created_at": "2024-03-17T10:00:00Z"
      }
  }

#### PUT a Comment 

- **Method:** PUT
- **Endpoint:** `localhost:8085/api/v1/comments`
- **Request Body:**
  ```json
  {
          "id":1
          "content": "Sample comment content",
          "post_id": 2,
          "username": "sample_user",
          "created_at": "2024-03-17T10:00:00Z"
      }
- **Sample Response Body:**
  ```json
  {
      "status_code": 200,
      "status_type": 1,
      "status_desc": "Success",
      "results_object": {
          "id": 1,
          "content": "Sample comment content",
          "post_id": 2,
          "username": "sample_user",
          "created_at": "2024-03-17T10:00:00Z"
      }
  }
#### DELETE a Comment 

- **Method:** DELETE
- **Endpoint:** `localhost:8085/api/v1/comments/{comment_id}`
- **Request Body:** None
- **Sample Response Body:** None
- more on other endpoints: https://documenter.getpostman.com/view/12616778/2sA2xnxVfe

#### Create Post responses, PUT posts responses ,GET single posts
      ```json
    {
        "status_code": 200,
        "status_type": 1,
        "status_desc": "Request processed successfully",
        "results_object": {
            "id": 48,
            "title": "post 2",
            "content": "posts content",
            "username": "kelvinmaxwell1",
            "created_at": "2024-03-17T09:20:34.711+00:00"
        }
    }

#### GET all Post responses
      ```json
        {
        "status_code": 200,
        "status_type": 1,
        "status_desc": "Request processed successfully",
        "results_object": [
            {
                "id": 48,
                "title": "post 1",
                "content": "posts content",
                "username": "kelvinmaxwell1",
                "created_at": "2024-03-17T09:19:04.097+00:00"
            }
        ]
        }

#### sign in response
    ```` json
    {
        "status_code": 200,
        "status_type": 1,
        "status_desc": "Request processed successfully",
        "results_object": {
            "token": "eyJhbGciOiJIUzUxMiJ9.eyJSb2xlcyI6WyJBRE1JTiJdLCJzdWIiOiJrZWx2aW5tYXh3ZWxsMSIsImlhdCI6MTcxMDY2NzA4NSwiZXhwIjoxNzEwNzUzNDg1fQ.NfFYqhSHJBGebC-AKGqTiywOchNrgfH3JbdDDvNSrEZ7UDhZuNxPRwgxMaoirt-IKv3kZnd92v6ubgNJ7CQzKQ"
        }
    }

#### sign up response end point: localhost:8085/api/v1/auth/signup
    ``` json
    {
        "status_code": 200,
        "status_type": 1,
        "status_desc": "Request processed successfully",
        "results_object": {
            "id": 99,
            "username": "kelvinmaxwell1",
            "email": "kelvinmaxwell83@gmail.com",
            "password": "$2a$10$ffJJ3l6RfDc4FKXhkDRtNewBHV0xPuniKp9jmgRBxtb6p7qINlUN.",
            "status": 1,
            "role": "[ADMIN]"
        }
    }
   
#### update password response:localhost:8085/api/v1/auth/updatepassword
 ``` json
      {
        "status_code": 200,
        "status_type": 1,
        "status_desc": "Request processed successfully",
        "results_object": {
            "id": 99,
            "old_password": "123456789",
            "new_password": "1234567890"
        }
    }








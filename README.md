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
      "results_object": {
          "id": 1,
          "content": "Sample comment content",
          "post_id": 2,
          "username": "sample_user",
          "created_at": "2024-03-17T10:00:00Z"
      }
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

  



more on other endpoints: https://documenter.getpostman.com/view/12616778/2sA2xnxVfe

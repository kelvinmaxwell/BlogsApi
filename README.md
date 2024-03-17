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
Delete a Comment by ID
Method: DELETE
Endpoint: localhost:8085/api/v1/comments/{comment_id}
Request Body: None
Sample Response Body:
json
Copy code
{
    "status_code": 204,
    "status_type": 1,
    "status_desc": "Success",
    "results_object": null
}
Get All Comments
Method: GET
Endpoint: localhost:8085/api/v1/comments
Request Body: None
Sample Response Body:
json



{
    "status_code": 200,
    "status_type": 1,
    "status_desc": "Success",
    "results_object": [
        {
            "id": 1,
            "content": "Sample comment content 1",
            "post_id": 2,
            "username": "sample_user",
            "created_at": "2024-03-17T10:00:00Z"
        },
        {
            "id": 2,
            "content": "Sample comment content 2",
            "post_id": 3,
            "username": "sample_user",
            "created_at": "2024-03-17T11:00:00Z"
        }
    ]
}


Create a New Comment
Method: POST
Endpoint: localhost:8085/api/v1/comments
Request Body:
json
Copy code
{
    "content": "new comment",
    "post_id": 2,
    "username": "kelvinmaxwell"
}
Sample Response Body:
json

{
    "status_code": 201,
    "status_type": 1,
    "status_desc": "Created",
    "results_object": {
        "id": 3,
        "content": "new comment",
        "post_id": 2,
        "username": "kelvinmaxwell",
        "created_at": "2024-03-17T12:00:00Z"
    }
}
Update a Comment
Method: PUT
Endpoint: localhost:8085/api/v1/comments
Request Body:
json
Copy code
{
    "id": 1,
    "content": "updated comment content",
    "post_id": 2,
    "username": "kelvinmaxwell",
    "created_at": "2024-03-17T13:00:00Z"
}
Sample Response Body:
json
Copy code
{
    "status_code": 200,
    "status_type": 1,
    "status_desc": "Success",
    "results_object": {
        "id": 1,
        "content": "updated comment content",
        "post_id": 2,
        "username": "kelvinmaxwell",
        "created_at": "2024-03-17T13:00:00Z"
    }
}



more on other endpoints: https://documenter.getpostman.com/view/12616778/2sA2xnxVfe

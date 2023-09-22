# Record a grade

Rcord a grade of a course for a utorid. Users can only log grades for themselves.

**URL** : `/grade`

**Method** : `POST`

**Auth required** : Required in header `Authorization`.

**Required Request Body**
```json
{
    "course": "The course code",
    "grade": "The grade to log"
}
```
## Success Responses

**Condition** : Access to the utorid is verified by the authorization token, and a grade for the course has not been logged before.

**Code** : `200 OK`

**Content example** : 

```json
{
    "id": "64b85b6ce66b09ca82769e68",
    "message": "Grade created successfully",
    "status_code": 200
}
```

## Error Response

### Grade already exists.

**Condition** : The grade of this course for this student has already been logged.

**Code** : `400 BAD REQUEST`

**Content example** :

```json
{
    "message": "Grade already exists",
    "status_code": 400
}
```

### Grade is not valid.

**Condition** : The grade is not an integer between 0 - 100.

**Code** : `400 BAD REQUEST`

**Content example** :

```json
{
    "message": "grade must be an integer between 0 and 100",
    "status_code": 400
}
```

### API Token is invalid

**Condition** : The given authorization token doesn't match with the ones that have the access to the utorid. Or the authorization token doesn't exist. Students need to log in their teach lab account to see the token at https://grade-logging-api.chenpan.ca/signUp?utorid=
**Code** : `401`

**Content example** :

```json
{
    "message": "Invalid token",
    "status_code": 401
}
```

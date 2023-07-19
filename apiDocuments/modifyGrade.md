# Modify a grade

Modify a grade of a course for a utorid.

**URL** : `/grade`

**Method** : `PUT`

**Auth required** : Required in header `Authorization`.

**Required Request Body**
```json
{
    "utorid": "The utorid",
    "course": "The course code",
    "grade": "The new grade"
}
```
## Success Responses

**Condition** : Access to the utorid is verified by the authorization token, and a grade for the course has not been logged before.

**Code** : `200 OK`

**Content example** : 

```json
{
    "message": "Grade updated successfully",
    "status_code": 200
}
```

## Error Response

### Grade not exist.

**Condition** : The grade of this course for this student doesn't exist..

**Code** : `404 NOT FOUND`

**Content example** :

```json
{
    "message": "The grade does not exist, please create it first using POST /grade",
    "status_code": 404
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

**Condition** : The given authorization token doesn't match with the ones that have the access to the utorid. Or the authorization token doesn't exist. Students need to log in their teach lab account to see the token at https://wwwcgi.teach.cs.toronto.edu/~csc207h/cgi-bin/fall/test/test-cgi.
**Code** : `401`

**Content example** :

```json
{
    "message": "Invalid token",
    "status_code": 401
}
```

### Server Error

**Condition** : The backend server has an issue.

**Code** : `500 Internal Server Error`

**Content example** :

```json
{
   "status_code": 500,
   "message": "Error retrieving grade"
}, 500
```
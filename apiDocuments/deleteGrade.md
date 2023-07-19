# Delete a grade

Delete a grade of a course for a utorid.

**URL** : `/grade`

**Method** : `DELETE`

**Auth required** : Required in header `Authorization`.

**Required Request Body**

```json
{
    "utorid": "The utorid",
    "course": "The course code",
}
```

## Success Responses

**Condition** : Access to the utorid is verified by the authorization token, and a grade for the course has been logged before (so there is something to delete).

**Code** : `200 OK`

**Content example** : 

```json
{
    "message": "Grade deleted successfully",
    "status_code": 200
}
```

## Error Response

### Grade not exist

**Condition** : The grade of this course for this student doesn't exist.

**Code** : `404 NOT FOUND`

**Content example** :

```json
{
    "message": "The grade does not exist, there's no need to delete it.",
    "status_code": 404
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
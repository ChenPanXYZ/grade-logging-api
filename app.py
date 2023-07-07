from flask import Flask
from pymongo import MongoClient
from pymongo.errors import PyMongoError
from flask import request
from config import MONGO_DB_CONNECTION_STRING

client = MongoClient(MONGO_DB_CONNECTION_STRING)
db = client['grade-logging-api']
GRADE = db['grade']
# every document has the following structure:
# {
#     "_id": ObjectId("5f9b0b4b9c9d6b0b9b3b3b3b"),
#     "utorid": "chenpan1",
#     "course": "CSC301",
#     "grade": 100

app = Flask(__name__)

# An API that creates a grade document.
# The request body should be a JSON object with the following fields:
# utorid: the utorid of the student
# course: the course code
# grade: the grade of the student
# The response body should be a JSON object with the following fields:
# status: a code
# message: "Grade created successfully" if the grade document is created successfully, "Error creating grade" otherwise
# id: the id of the grade document created
@app.route('/grade', methods=['POST'])
def create_grade():
    try:
        utorid = request.json['utorid'] if 'utorid' in request.json else None
        course = request.json['course'] if 'course' in request.json else None
        grade = request.json['grade'] if 'grade' in request.json else None

        if not utorid or not course or not grade:
            return {
                "status": 400,
                "message": "utorid, course, and grade are required"
            }
        
        # check if the grade is valid.
        if not isinstance(grade, int) or grade < 0 or grade > 100:
            return {
                "status": 400,
                "message": "grade must be an integer between 0 and 100"
            }

        # check if the grade document already exists

        the_doc = GRADE.find_one({
            "utorid": utorid,
            "course": course
        })
        if the_doc:
            return {
                "status": 400,
                "message": "Grade already exists"
            }
        grade_id = GRADE.insert_one({
            "utorid": utorid,
            "course": course,
            "grade": grade
        }).inserted_id
        return {
            "status": 200,
            "message": "Grade created successfully",
            "id": str(grade_id)
        }
    except PyMongoError as e:
        print(e)
        return {
            "status": 500,
            "message": "Error creating grade"
        }
    
# An API that returns a grade document, it's a get request with the following path: grade/course/utorid
# The response body should be a JSON object with the following fields:
# status: a code
# message: "Grade retrieved successfully" if the grade document is retrieved successfully, "Error retrieving grade" otherwise
# grade: the grade of the student
@app.route('/grade/<course>/<utorid>', methods=['GET'])
def get_grade(course, utorid):
    try:
        the_doc = GRADE.find_one({
            "utorid": utorid,
            "course": course
        })
        if not the_doc:
            return {
                "status": 404,
                "message": "Grade not found"
            }
        return {
            "status": 200,
            "message": "Grade retrieved successfully",
            "grade": the_doc['grade']
        }
    except PyMongoError as e:
        print(e)
        return {
            "status": 500,
            "message": "Error retrieving grade"
        }
    

# An API that updates a grade document.
# The request body should be a JSON object with the following fields:
# utorid: the utorid of the student
# course: the course code
# grade: the grade of the student
# The response body should be a JSON object with the following fields:
# status: a code
# message: "Grade updated successfully" if the grade document is updated successfully, "Error updating grade" otherwise
@app.route('/grade', methods=['PUT'])
def update_grade():
    try:
        utorid = request.json['utorid'] if 'utorid' in request.json else None
        course = request.json['course'] if 'course' in request.json else None
        grade = request.json['grade'] if 'grade' in request.json else None

        if not utorid or not course or not grade:
            return {
                "status": 400,
                "message": "utorid, course, and grade are required"
            }
        
        # check if the grade is valid.
        if not isinstance(grade, int) or grade < 0 or grade > 100:
            return {
                "status": 400,
                "message": "grade must be an integer between 0 and 100"
            }

        # check if the grade document already exists

        the_doc = GRADE.find_one({
            "utorid": utorid,
            "course": course
        })
        if not the_doc:
            return {
                "status": 404,
                "message": "The grade does not exist, please create it first using POST /grade"
            }
        
        GRADE.update_one({
            "_id": the_doc['_id']
        }, {
            "$set": {
                "grade": grade
            }
        })
        return {
            "status": 200,
            "message": "Grade updated successfully"
        }
    except PyMongoError as e:
        print(e)
        return {
            "status": 500,
            "message": "Error updating grade"
        }
    

# An API that deletes a grade document.
# The request body should be a JSON object with the following fields:
# utorid: the utorid of the student
# course: the course code
# The response body should be a JSON object with the following fields:
# status: a code
# message: "Grade deleted successfully" if the grade document is deleted successfully, "Error deleting grade" otherwise
@app.route('/grade', methods=['DELETE'])
def delete_grade():
    try:
        utorid = request.json['utorid'] if 'utorid' in request.json else None
        course = request.json['course'] if 'course' in request.json else None

        if not utorid or not course:
            return {
                "status": 400,
                "message": "utorid, course are required"
            }

        # check if the grade document already exists

        the_doc = GRADE.find_one({
            "utorid": utorid,
            "course": course
        })
        if not the_doc:
            return {
                "status": 404,
                "message": "The grade does not exist, there's no need to delete it."
            }
        
        GRADE.delete_one({
            "_id": the_doc['_id']
        })
        return {
            "status": 200,
            "message": "Grade deleted successfully"
        }
    except PyMongoError as e:
        print(e)
        return {
            "status": 500,
            "message": "Error updating grade"
        }


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=20110, debug=True, threaded=True)
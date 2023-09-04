package Gateway;
import org.json.JSONException;
import Entity.Grade;

public interface GradeDB {
    // Question: The dependency can go inwards, so I feel we can use the Grade class here (which is entity).
    Grade getGrade(String utorid, String course);

    // Return a list of arrays
    Grade[] getAllGrades(String utorid);

    Grade logGrade(String course, int grade) throws JSONException;
}

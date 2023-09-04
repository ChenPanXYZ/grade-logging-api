package UseCase;

import Entity.Grade;
import Gateway.GradeDB;
import org.json.JSONException;

public final class GetGradeUseCase {
    private final GradeDB gradeDB;

    public GetGradeUseCase(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    public Grade getGrade(String utorid, String course) {
        return gradeDB.getGrade(utorid, course);
    }
}

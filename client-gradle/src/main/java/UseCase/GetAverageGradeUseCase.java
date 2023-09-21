package UseCase;
import Entity.Grade;
import Gateway.GradeDB;
import org.json.JSONException;

public final class GetAverageGradeUseCase {
    private final GradeDB gradeDB;

    public GetAverageGradeUseCase(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    public float getAverageGrade(String course) {
        // TODO: Get average grade for all students in your team.
        // Some useful gateways: 1. get my team [Not Written]. 2. get grade.
        return 0; // not correct.
    }
}

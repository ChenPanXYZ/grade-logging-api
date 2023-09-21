package UseCase;
import Entity.Team;
import Gateway.GradeDB;
import org.json.JSONException;

public final class FormTeamUseCase {
    private final GradeDB gradeDB;

    public FormTeamUseCase(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    public Team formTeam(String name) {
        return gradeDB.formTeam(name);
        // Need to calculate the average. every grade has a field called grade (which is an int).
        // We need to sum all the grades and divide by the number of grades.
    }
}

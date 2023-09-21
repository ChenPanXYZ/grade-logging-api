package UseCase;
import Entity.Team;
import Gateway.GradeDB;
import org.json.JSONException;

public final class LeaveTeamUseCase {
    private final GradeDB gradeDB;

    public LeaveTeamUseCase(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    public void leaveTeam() {
        gradeDB.leaveTeam();
        // Need to calculate the average. every grade has a field called grade (which is an int).
        // We need to sum all the grades and divide by the number of grades.
    }
}

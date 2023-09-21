package UseCase;
import Entity.Team;
import Gateway.GradeDB;
import org.json.JSONException;

public final class JoinTeamUseCase {
    private final GradeDB gradeDB;

    public JoinTeamUseCase(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    public Team joinTeam(String name) {
        return gradeDB.joinTeam(name);
    }
}

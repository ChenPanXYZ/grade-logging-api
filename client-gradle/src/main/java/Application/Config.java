package Application;

import Gateway.GradeDB;
import Gateway.MongoGradeDB;
import UseCase.*;

public class Config {
    private final GradeDB gradeDB = new MongoGradeDB();

    public GetGradeUseCase getGradeUseCase() {
        return new GetGradeUseCase(gradeDB);
    }

    public LogGradeUseCase logGradeUseCase() {
        return new LogGradeUseCase(gradeDB);
    }

    public FormTeamUseCase formTeamUseCase () {
        return new FormTeamUseCase(gradeDB);
    }

    public JoinTeamUseCase JoinTeamUseCase () {
        return new JoinTeamUseCase(gradeDB);
    }

    public LeaveTeamUseCase LeaveTeamUseCase () {
        return new LeaveTeamUseCase(gradeDB);
    }

    public GetAverageGradeUseCase GetAverageGradeUseCase () {
        return new GetAverageGradeUseCase(gradeDB);
    }
}

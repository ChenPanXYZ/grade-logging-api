package Application;

import Gateway.GradeDB;
import Gateway.MongoGradeDB;
import UseCase.GetGradeUseCase;
import UseCase.LogGradeUseCase;

public class Config {
    private final GradeDB gradeDB = new MongoGradeDB();

    public GetGradeUseCase getGradeUseCase() {
        return new GetGradeUseCase(gradeDB);
    }

    public LogGradeUseCase logGradeUseCase() {
        return new LogGradeUseCase(gradeDB);
    }
}

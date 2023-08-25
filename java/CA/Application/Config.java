public class Config {
    private final GradeDB gradeDB = new MongoGradeDB();

    public GetGradeUseCase getGradeUseCase() {
        return new GetGradeUseCase(gradeDB);
    }

    public LogGradeUseCase logGradeUseCase() {
        return new LogGradeUseCase(gradeDB);
    }
}

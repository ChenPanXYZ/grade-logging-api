import org.json.JSONException;

public final class GetAverageGradeUseCase {
    private final GradeDB gradeDB;

    public GetAverageGradeUseCase(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    public float getAverageGrade(String utorid) {
        Grade[] grades = gradeDB.getAllGrades(utorid);
        // Need to calculate the average. every grade has a field called grade (which is an int).
        // We need to sum all the grades and divide by the number of grades.
        int sum = 0;
        for (Grade grade : grades) {
            sum += grade.getGrade();
        }
        return sum / grades.length;
    }
}

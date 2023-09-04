import Entity.Grade;
import Gateway.GradeDB;
import UseCase.GetGradeUseCase;
import UseCase.LogGradeUseCase;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class LogGradeUseCaseTest {
    @Mock
    private GradeDB gradeDB;

    private LogGradeUseCase logGradeUseCase;
    private GetGradeUseCase getGradeUseCase; // For getting the result to verify the result.

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logGradeUseCase = new LogGradeUseCase(gradeDB);
        getGradeUseCase = new GetGradeUseCase(gradeDB);
    }
    @Test
    void testLogGradeWhenGradeNotExist() throws JSONException {
        // Arrange
        // The grade isn't there yet. So, we know that the DB will take in these values.
        String utorid = "tester";
        String course = "CSC207";
        int valueToLog = 99;
        Grade expectedGrade = Grade.builder()
                .utorid(utorid)
                .course(course)
                .grade(valueToLog)
                .build();

        // Define the behavior of the mock
        when(gradeDB.logGrade(course, valueToLog)).thenReturn(null);

        when(gradeDB.getGrade("tester", course)).thenReturn(expectedGrade);
        // Act
        Grade result = getGradeUseCase.getGrade(utorid, course);


        // Assert
        assertEquals(expectedGrade, result);
    }

}
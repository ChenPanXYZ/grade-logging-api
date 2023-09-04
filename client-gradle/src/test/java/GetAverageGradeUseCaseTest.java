import Entity.Grade;
import Gateway.GradeDB;
import UseCase.GetAverageGradeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class GetAverageGradeUseCaseTest {
    @Mock
    private GradeDB gradeDB;

    private GetAverageGradeUseCase getAverageGradeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAverageGradeUseCase = new GetAverageGradeUseCase(gradeDB);
    }
    @Test
    void testGetAverageGrade1() {
        // Build an array of Grades.
        Grade[] expectedGradesFromGateway = new Grade[3];
        expectedGradesFromGateway[0] = Grade.builder()
                .utorid("t1chenpa")
                .course("CSC207")
                .grade(85)
                .build();
        expectedGradesFromGateway[1] = Grade.builder()
                .utorid("t1chenpa")
                .course("CSC148")
                .grade(86)
                .build();
        expectedGradesFromGateway[2] = Grade.builder()
                .utorid("t1chenpa")
                .course("CSC165")
                .grade(91)
                .build();

        // Define the behavior of the mock
        when(gradeDB.getAllGrades("t1chenpa")).thenReturn(expectedGradesFromGateway);

        // Act
        float result = getAverageGradeUseCase.getAverageGrade("t1chenpa");

        // Assert
        assertEquals((85+86+91)/3, result);
    }
}
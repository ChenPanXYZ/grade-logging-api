import Entity.Grade;
import Gateway.GradeDB;
import UseCase.GetGradeUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class GetGradeUseCaseTest {
    @Mock
    private GradeDB gradeDB;

    private GetGradeUseCase getGradeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getGradeUseCase = new GetGradeUseCase(gradeDB);
    }
    @Test
    void testGetGradeReturnsGrade() {
        // Arrange
        String utorid = "t1chenpa";
        String course = "CSC207";
        int grade = 100;
        Grade expectedGrade = Grade.builder()
                .utorid(utorid)
                .course(course)
                .grade(grade)
                .build();

        // Define the behavior of the mock
        when(gradeDB.getGrade(utorid, course)).thenReturn(expectedGrade);

        // Act
        Grade result = getGradeUseCase.getGrade(utorid, course);

        // Assert
        assertEquals(expectedGrade, result);
    }

    @Test
    void testGetGradeReturnsNullWhenGradeNotFound() {
        // Arrange
        String utorid = "t2chenpa";
        String course = "CSC207";

        // Define the behavior of the mock to return null when no grade is found
        when(gradeDB.getGrade(utorid, course)).thenReturn(null);

        // Act
        Grade result = getGradeUseCase.getGrade(utorid, course);

        // Assert
        assertNull(result);
    }

}
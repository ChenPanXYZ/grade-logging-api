import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String menu(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your input: ");
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static void main(String[] args) throws IOException {
        // Let use choose an option.

        String option = menu("What do you want to do: 1. Get a grade. 2. Log a grade. 3. Modify a grade 4. Delete a grade \n 5. Form a team. 6. Request to join a team  7. See my team members 8. Handle new member request.");

        // Print option.
        // check if option == 1 regardless of type).

        if (option.equals("1")) {
           GetGrade getGrade = new GetGrade();
           getGrade.getGrade();
        }
        else if (option.equals("2")) {
            LogGrade logGrade = new LogGrade();
            logGrade.logGrade();
        }
        else if (option.equals("3")) {
            ModifyGrade modifyGrade = new ModifyGrade();
            modifyGrade.modifyGrade();
        }
        else if (option.equals("4")) {
            DeleteGrade deleteGrade = new DeleteGrade();
            deleteGrade.deleteGrade();
        }

        else if (option.equals("5")) {
            FormTeam formTeam = new FormTeam();
            formTeam.formTeam();
        }
        else if (option.equals("6")) {
            RequestToJoinTeam requestToJoinTeam = new RequestToJoinTeam();
            requestToJoinTeam.requestToJoinTeam();
        }
        else if (option.equals("7")) {
            SeeMyTeamMembers seeMyTeamMembers = new SeeMyTeamMembers();
            seeMyTeamMembers.seeMyTeamMembers();
        }
        else if (option.equals("8")) {
            HandleNewMemberRequest handleNewMemberRequest = new HandleNewMemberRequest();
            handleNewMemberRequest.handleNewMemberRequest();
        }

        else if (option.equals("9")) {
            LeaveTeam leaveTeam = new LeaveTeam();
            leaveTeam.leaveTeam();
        }
    }

}
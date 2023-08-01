import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

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

    public static void main(String[] args) throws IOException, JSONException, UnirestException {
        // check if environment variable is set or not.
        if(System.getenv("API_TOKEN") == null) {
            System.out.println("Please add API_TOKEN in your project's environment variables.");
            return;
        }
        boolean exitRequested = false;

        while (!exitRequested) {
            // Let the user choose an option.
            String option = menu("What do you want to do: 1. Get a grade. 2. Log a grade. 3. Modify a grade 4. Delete a grade \n 5. Form a team. 6. Request to join a team  7. See my team members 8. Handle new member request. 9. Leave team 10. Exit");

            // Check the selected option.
            switch (option) {
                case "1":
                    GetGrade getGrade = new GetGrade();
                    getGrade.getGrade();
                    break;
                case "2":
                    LogGrade logGrade = new LogGrade();
                    logGrade.logGrade();
                    break;
                case "3":
                    ModifyGrade modifyGrade = new ModifyGrade();
                    modifyGrade.modifyGrade();
                    break;
                case "4":
                    DeleteGrade deleteGrade = new DeleteGrade();
                    deleteGrade.deleteGrade();
                    break;
                case "5":
                    FormTeam formTeam = new FormTeam();
                    formTeam.formTeam();
                    break;
                case "6":
                    RequestToJoinTeam requestToJoinTeam = new RequestToJoinTeam();
                    requestToJoinTeam.requestToJoinTeam();
                    break;
                case "7":
                    SeeMyTeamMembers seeMyTeamMembers = new SeeMyTeamMembers();
                    seeMyTeamMembers.seeMyTeamMembers();
                    break;
                case "8":
                    HandleNewMemberRequest handleNewMemberRequest = new HandleNewMemberRequest();
                    handleNewMemberRequest.handleNewMemberRequest();
                    break;
                case "9":
                    LeaveTeam leaveTeam = new LeaveTeam();
                    leaveTeam.leaveTeam();
                    break;
                case "10":
                    exitRequested = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class GetGrade {
    // two fields: utorid, and course.
    private String utorid;
    private String course;
    // constructor
    public GetGrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your utorid: ");
        utorid = scanner.nextLine();
        System.out.print("Enter your course: ");
        course = scanner.nextLine();
    }


    public void getGrade() throws IOException {
        String GET_URL = String.format("https://grade-logging-api.chenpan.ca/grade?course=%s&utorid=%s", this.course, this.utorid); // TODO
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty ("Authorization", System.getenv("API_TOKEN")); // TODO
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);

            }
            JSONObject responseObj = new JSONObject(response.toString());
            in.close();
            //example: {"utorid":"t1chenpa","status_code":200,"grade":86,"message":"Grade retrieved successfully"}
            System.out.println(responseObj.get("grade")); // TODO
        }

        // TODO: Students are required to read API to understand what is the response code for each case.
        else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("Grade not found.");
        }
        else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
            System.out.println("400 Bad Request");
        }

        // 401
        else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
            System.out.println("401 Unauthorized");
        }

        else {
            System.out.println("GET request did not work.");
        }
    }
}

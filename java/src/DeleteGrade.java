import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class DeleteGrade {
    // three fields: utorid, course, and grade.
    private String utorid;
    private String course;
    // constructor
    public DeleteGrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your utorid: ");
        utorid = scanner.nextLine();
        System.out.print("Enter your course: ");
        course = scanner.nextLine();
    }

    public void deleteGrade() throws IOException {
        String POST_URL = String.format("https://grade-logging-api.chenpan.ca/grade"); // TODO
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty ("Authorization", System.getenv("API_TOKEN")); // TODO
        con.setRequestProperty("Content-type", "application/json"); // TODO
        // TODO: set request body.
        JSONObject requestBody = new JSONObject();
        requestBody.put("utorid", this.utorid);
        requestBody.put("course", this.course);
        con.setDoOutput(true);
        con.getOutputStream().write(requestBody.toString().getBytes("UTF-8"));
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
            System.out.println(responseObj); // TODO
        }

        // TODO: Students are required to read API to understand what is the response code for each case.
    }
}

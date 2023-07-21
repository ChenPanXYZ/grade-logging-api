import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class RequestToJoinTeam {
    private String utorid;
    // constructor
    public RequestToJoinTeam() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your utorid: ");
        utorid = scanner.nextLine();
    }

    public void getAllTeams() throws IOException {
        String GET_URL = String.format("https://grade-logging-api.chenpan.ca/teams"); // TODO
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty ("Authorization", System.getenv("API_TOKEN")); // TODO
        con.setRequestProperty("Content-type", "application/json"); // TODO
        // TODO: set request body.
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
        else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("Grade not found.");
        }

        // 409
        else if (responseCode == HttpURLConnection.HTTP_CONFLICT) {
            System.out.println("409 Conflict");
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

    public void requestToJoinTeam() throws IOException {
        getAllTeams();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the team name you want to join: ");
        String name = scanner.nextLine();

        String POST_URL = String.format("https://grade-logging-api.chenpan.ca/requestJoinTeam"); // TODO
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty ("Authorization", System.getenv("API_TOKEN")); // TODO
        con.setRequestProperty("Content-type", "application/json"); // TODO
        // TODO: set request body.
        JSONObject requestBody = new JSONObject();
        requestBody.put("utorid", this.utorid);
        requestBody.put("name", name);
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
        else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("Grade not found.");
        }

        // 409
        else if (responseCode == HttpURLConnection.HTTP_CONFLICT) {
            System.out.println("409 Conflict");
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

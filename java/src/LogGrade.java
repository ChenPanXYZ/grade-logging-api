import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class LogGrade {
    // three fields: utorid, course, and grade.
    private String course;
    private int grade;
    // constructor
    public LogGrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your course: ");
        course = scanner.nextLine();
        System.out.print("Enter your grade: ");
        grade = scanner.nextInt();
    }

    public void logGrade() throws IOException, JSONException, UnirestException {
        String POST_URL = String.format("https://grade-logging-api.chenpan.ca/grade"); // TODO
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty ("Authorization", System.getenv("API_TOKEN")); // TODO
        con.setRequestProperty("Content-type", "application/json"); // TODO
        // TODO: set request body.
        JSONObject requestBody = new JSONObject();
        requestBody.put("course", this.course);
        requestBody.put("grade", this.grade);
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

        // Okhttp3:

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("course", this.course);
//        requestBody.put("grade", this.grade);
//        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        Request request = new Request.Builder()
//                .url("https://grade-logging-api.chenpan.ca/grade")
//                .method("POST", body)
//                .addHeader("Authorization", "qRQxwHHpN32cc0YMW1T01T0j6J60aJnP")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//        if(response.code() == 200) {
//            // print response body
//            System.out.println(response.body().string());
//        }

        // Unirest:

//        JSONObject requestBody = new JSONObject();
//        requestBody.put("course", this.course);
//        requestBody.put("grade", this.grade);
//        HttpResponse<String> response = Unirest.post("https://grade-logging-api.chenpan.ca/grade")
//                .header("Authorization", "qRQxwHHpN32cc0YMW1T01T0j6J60aJnP")
//                .header("Content-Type", "application/json")
//                .body(requestBody.toString())
//                .asString();
//
//        if(response.getStatus() == 200) {
//            // print response body
//            System.out.println(response.getBody());
//        }

    }
}

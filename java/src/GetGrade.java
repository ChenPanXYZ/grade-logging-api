import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
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
        System.out.print("Enter the utorid: ");
        utorid = scanner.nextLine();
        System.out.print("Enter the course: ");
        course = scanner.nextLine();
    }


    public void getGrade() throws IOException, JSONException, UnirestException {
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

        // Okhttp3:

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url(String.format("https://grade-logging-api.chenpan.ca/grade?course=%s&utorid=%s", this.course, this.utorid))
//                .addHeader("Authorization", "qRQxwHHpN32cc0YMW1T01T0j6J60aJnP")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//
//        // response body is a json object.
//        JSONObject responseObj = new JSONObject(response.body().string());
//        if(response.code() == 200) {
//            System.out.println(responseObj.get("grade"));
//        }

        // Unirest:
//        HttpResponse<String> response = Unirest.get("https://grade-logging-api.chenpan.ca/grade?course=csc207&utorid=t1chenpa")
//                .header("Authorization", "qRQxwHHpN32cc0YMW1T01T0j6J60aJnP")
//                .header("Content-Type", "application/json")
//                .asString();
//
//        JSONObject responseObj = new JSONObject(response.getBody());
//        if(response.getStatus() == 200) {
//            System.out.println(responseObj.get("grade"));
//        }

    }
}

import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class DeleteGrade extends Subpage{
    // three fields: utorid, course, and grade.
    private JButton submitButton;
    private JTextField studentField, courseField;
    // constructor

    public DeleteGrade(CardLayout cardLayout, JPanel cards) {
        setLayout(new GridLayout(4, 2));

        submitButton = new JButton("Submit");
        studentField = new JTextField();
        courseField = new JTextField();
        submitButton = new JButton("Submit");

        add(new JLabel("Student:"));
        add(studentField);
        add(new JLabel("Course:"));
        add(courseField);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the grade retrieval logic here
                String utorid = studentField.getText();
                String course = courseField.getText();

                // For example: Display the retrieved grade in a dialog box
                try {
                    JSONObject response = DeleteGrade.deleteGrade(utorid, course);
                    JOptionPane.showMessageDialog(cards, response.getString("message"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                }

                // Switch back to the main panel
                cardLayout.show(cards, "main");
                clear();
            }
        });
    }


    private void clear() {
        courseField.setText("");
        studentField.setText("");
    }

    public static JSONObject deleteGrade(String utorid, String course) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("course", course);
        requestBody.put("utorid", utorid);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://grade-logging-api.chenpan.ca/grade")
                .method("DELETE", body)
                .addHeader("Authorization", "qRQxwHHpN32cc0YMW1T01T0j6J60aJnP")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject responseObj = new JSONObject(response.body().string());
        return responseObj;
    }

    @Override
    public void run() throws IOException {

    }
}

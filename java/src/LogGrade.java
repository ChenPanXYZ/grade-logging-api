import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
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
public class LogGrade extends JPanel {
    private JButton submitButton, goBackButton;
    private JTextField courseField, gradeField;
    // constructor
    public LogGrade(CardLayout cardLayout, JPanel cards) {
        setLayout(new GridLayout(4, 2));

        submitButton = new JButton("Submit");
        courseField = new JTextField();
        gradeField = new JTextField();
        submitButton = new JButton("Submit");

        // make a go back button.

        goBackButton = new JButton("Go Back to Menu");

        add(new JLabel("Course:"));
        add(courseField);
        add(new JLabel("Grade:"));
        add(gradeField);
        add(submitButton);
        add(goBackButton);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "main");
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the grade retrieval logic here
                String course = courseField.getText();
                String grade = gradeField.getText();

                // For example: Display the retrieved grade in a dialog box
                try {
                    JOptionPane.showMessageDialog(cards, LogGrade.logGrade(course, grade));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                } catch (UnirestException ex) {
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
        gradeField.setText("");
    }

    public static String logGrade(String course, String grade) throws IOException, JSONException, UnirestException {
        // Okhttp3:

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("course", course);
        requestBody.put("grade", grade);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://grade-logging-api.chenpan.ca/grade")
                .method("POST", body)
                .addHeader("Authorization", "qRQxwHHpN32cc0YMW1T01T0j6J60aJnP")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject responseObj = new JSONObject(response.body().string());
        if(response.code() == 200) {
            return ("Grade logged successfully!");
        }
        else {
            return((String) responseObj.get("message"));
        }

    }
}

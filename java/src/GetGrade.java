import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GetGrade extends JPanel{
    // two fields: utorid, and course.
    private JButton submitButton;
    private JTextField studentField, courseField;
    // constructor
    public GetGrade(CardLayout cardLayout, JPanel cards) {
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
                    JOptionPane.showMessageDialog(cards, GetGrade.getGrade(utorid, course));
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
        studentField.setText("");
    }


    public static String getGrade(String utorid, String course) throws IOException, JSONException, UnirestException {
        // Okhttp3:

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://grade-logging-api.chenpan.ca/grade?course=%s&utorid=%s", course, utorid))
                .addHeader("Authorization", "qRQxwHHpN32cc0YMW1T01T0j6J60aJnP")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        // response body is a json object.
        JSONObject responseObj = new JSONObject(response.body().string());
        if(response.code() == 200) {
            return (String.format("Grade is %s", responseObj.get("grade")));
        }


        return (String) responseObj.get("message");
    }
}

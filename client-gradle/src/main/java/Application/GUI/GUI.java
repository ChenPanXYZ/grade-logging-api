package Application.GUI;

import Application.Config;
import Entity.Grade;
import UseCase.GetGradeUseCase;
import UseCase.LogGradeUseCase;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public static void main(String[] args) {
        var config = new Config();

        var getGradeUseCase = config.getGradeUseCase();
        var logGradeUseCase = config.logGradeUseCase();


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grade GUI App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            JPanel defaultCard = createDefaultCard();
            JPanel getGradeCard = createGetGradeCard(getGradeUseCase);
            JPanel logGradeCard = createLogGradeCard(logGradeUseCase);

            cardPanel.add(defaultCard, "DefaultCard");
            cardPanel.add(getGradeCard, "GetGradeCard");
            cardPanel.add(logGradeCard, "LogGradeCard");

            JButton getGradeButton = new JButton("Get Grade");
            getGradeButton.addActionListener(e -> cardLayout.show(cardPanel, "GetGradeCard"));

            JButton logGradeButton = new JButton("Log Grade");
            logGradeButton.addActionListener(e -> cardLayout.show(cardPanel, "LogGradeCard"));

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(getGradeButton);
            buttonPanel.add(logGradeButton);

            frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    private static JPanel createDefaultCard() {
        JPanel defaultCard = new JPanel();
        defaultCard.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("Welcome to the Grade App (GUI Version)!");

        defaultCard.add(infoLabel, BorderLayout.CENTER);

        return defaultCard;
    }

    private static JPanel createGetGradeCard(GetGradeUseCase getGradeUseCase) {
        JPanel getGradeCard = new JPanel();
        getGradeCard.setLayout(new GridLayout(3, 2));

        JTextField utoridField = new JTextField(20);
        JTextField courseField = new JTextField(20);
        JButton getButton = new JButton("Get");

        JLabel resultLabel = new JLabel();

        getButton.addActionListener(e -> {
            String utorid = utoridField.getText();
            String course = courseField.getText();

            // Call GetGradeUseCase here.
            // You might also want to update some UI elements to indicate the success
            try{
                Grade grade = getGradeUseCase.getGrade(utorid, course);
                resultLabel.setText(String.format("Grade: %d", grade.getGrade()));
            }
            catch (Exception ex){
                resultLabel.setText(ex.getMessage());
            }
        });

        getGradeCard.add(new JLabel("UTORid:"));
        getGradeCard.add(utoridField);
        getGradeCard.add(new JLabel("Course:"));
        getGradeCard.add(courseField);
        getGradeCard.add(getButton);
        getGradeCard.add(resultLabel);

        return getGradeCard;
    }

    private static JPanel createLogGradeCard(LogGradeUseCase logGradeUseCase) {
        JPanel logGradeCard = new JPanel();
        logGradeCard.setLayout(new GridLayout(4, 2));
        JTextField courseField = new JTextField(20);
        JTextField gradeField = new JTextField(20);
        JButton logButton = new JButton("Log");
        JLabel resultLabel = new JLabel();

        logButton.addActionListener(e -> {
            String course = courseField.getText();
            String gradeStr = gradeField.getText();
            int grade = Integer.parseInt(gradeStr);

            try {
                logGradeUseCase.logGrade(course, grade);
                resultLabel.setText("Grade Added successfully.");
                courseField.setText("");
                gradeField.setText("");
            } catch (RuntimeException ex) {
                resultLabel.setText(ex.getMessage());
            }

        });
        logGradeCard.add(new JLabel("Course:"));
        logGradeCard.add(courseField);
        logGradeCard.add(new JLabel("Grade:"));
        logGradeCard.add(gradeField);
        logGradeCard.add(logButton);
        logGradeCard.add(resultLabel);


        return logGradeCard;
    }
}
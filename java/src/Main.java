import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private JPanel cards;
    private CardLayout cardLayout;

    private JTextField studentField, courseField, gradeField;
    private JButton submitButton;


    public Main() {
        initUI();
    }

    private void initUI() {
        setTitle("Grade Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cards = new JPanel(new CardLayout());
        cardLayout = (CardLayout) cards.getLayout();
        JPanel mainPanel = new JPanel();
        cards.add(mainPanel, "main");
        // Define subpages.

        // Get Grade.
        JButton getGradeButton = new JButton("Get a Grade");
        mainPanel.add(getGradeButton);
        getGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "getGradePage");
            }
        });
        cards.add(new GetGrade(cardLayout, cards), "getGradePage");

        // Log Grade
        JButton logGradeButton = new JButton("Log a Grade");
        mainPanel.add(logGradeButton);
        logGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "logGradePage");
            }
        });
        cards.add(new LogGrade(cardLayout, cards), "logGradePage");

        add(cards);

        pack();
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
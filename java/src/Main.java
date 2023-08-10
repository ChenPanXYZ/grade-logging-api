import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private JPanel cards;
    private CardLayout cardLayout;


    public Main() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Get Grade.
        JButton getGradeButton = new JButton("Get a Grade");
        // Log Grade
        JButton logGradeButton = new JButton("Log a Grade");
        JButton DeleteGradeButton = new JButton("Delete a Grade (for regrading purpose)");
        JButton SeeMyTeamMembersButton = new JButton("See my team members");
        JButton FormTeamButton = new JButton("Form a team");
        JButton HandleNewMemberRequestButton = new JButton("Handle new member request");
        JButton RequestToJoinTeamButton = new JButton("Join a team");
        JButton LeaveTeamButton = new JButton("Leave my team");

        // Add a text: Grade Management
        mainPanel.add(new JLabel("Grade Management"));
        mainPanel.add(getGradeButton);
        mainPanel.add(logGradeButton);
        mainPanel.add(DeleteGradeButton);
        // Add a text: Team Management
        mainPanel.add(new JLabel("Team Management"));
        mainPanel.add(SeeMyTeamMembersButton);
        mainPanel.add(FormTeamButton);
        mainPanel.add(HandleNewMemberRequestButton);
        mainPanel.add(RequestToJoinTeamButton);
        mainPanel.add(LeaveTeamButton);


        // Adding navigations.
        JButton[] buttons = {getGradeButton, logGradeButton, DeleteGradeButton, SeeMyTeamMembersButton, FormTeamButton, RequestToJoinTeamButton, LeaveTeamButton, HandleNewMemberRequestButton};
        Class<?>[] classes = {GetGrade.class, LogGrade.class, DeleteGrade.class, SeeMyTeamMembers.class, FormTeam.class, RequestToJoinTeam.class, LeaveTeam.class, HandleNewMemberRequest.class};
        String[] pageNames = {"getGradePage", "logGradePage", "deleteGradePage", "seeMyTeamMembersPage", "formTeamPage", "requestToJoinTeamPage", "leaveTeamPage", "handleNewMemberRequestPage"};
        Subpage[] cardComponents = new Subpage[buttons.length];
        for(int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cards, pageNames[finalI]);
                    try {
                        cardComponents[finalI].run();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            try
            {
                Subpage cardComponent = (Subpage) classes[i].getDeclaredConstructor(CardLayout.class, JPanel.class).newInstance(cardLayout, cards);
                cardComponents[i] = cardComponent;
                cards.add(cardComponent, pageNames[finalI]);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        add(cards);
        pack();
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        // The program starts here.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
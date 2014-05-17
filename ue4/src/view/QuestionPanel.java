package view;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import model.*;


public class QuestionPanel extends JPanel
{
    public Question question;
    public JButton answer0, answer1, answer2, answer3;
    private JButton[] buttons;
    private JPanel buttonPanel;
    private JLabel questionLabel;
    private ActionListener listener;

    public QuestionPanel(FancyUI ui)
    {

        final FancyUI fui = ui;
        questionLabel = new JLabel("question");
        this.add(questionLabel);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,2));
        this.add(buttonPanel);

        buttons = new JButton[4];
        answer0 = new JButton();
        buttons[0] = answer0;
        answer1 = new JButton();
        buttons[1] = answer1;
        answer2 = new JButton();
        buttons[2] = answer2;
        answer3 = new JButton();
        buttons[3] = answer3;

        listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                fui.answer(question, ((JButton) ae.getSource()).getText());

                System.out.println("meep");
            }
        };

        for (JButton button : buttons)
        {
            button.addActionListener(listener);
            buttonPanel.add(button);
        }

    }

    public void ask(Question question)
    {
        this.question = question;

        questionLabel.setText(question.getQuestion());

        for (int i=0; i<4; i++)
        {
            buttons[i].setText(question.getAnswers()[i]);
        }
    }
    public void addSupervisor(ActionListener supervisor)
    {
        for (JButton button : buttons)
        {
            button.addActionListener(supervisor);
        }
    }
}

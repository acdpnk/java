import java.awt.event.*;
import javax.swing.*;
import model.*;


public class QuestionPanel extends JPanel
{
    public Question question;
    private final FancyUI ui;
    public JButton answer0, answer1, answer2, answer3;
    private JButton[] buttons;
    private JPanel buttonPanel;
    private JLabel questionLabel;
    private ActionListener listener;

    public QuestionPanel(FancyUI ui)
    {
        this.ui = ui;
        questionLabel = new JLabel("question");
        this.add(questionLabel);
        buttonPanel = new JPanel();
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
                ui.answer(question, ((JButton) ae.getSource()).getText());

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

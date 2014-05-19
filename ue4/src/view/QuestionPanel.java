package view;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import model.*;


public class QuestionPanel extends JPanel
{
    public Question question;
    public JButton answer0, answer1, answer2, answer3, next;
    private JButton[] buttons;
    private JPanel buttonPanel;
    private JLabel questionLabel;
    private ActionListener listener;

    public QuestionPanel(FancyUI ui)
    {

        final FancyUI fui = ui;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        questionLabel = new JLabel("question");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // questionLabel gives a flying frak and jumps around ¯\(°_o)/¯

        this.add(questionLabel);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,2));
        this.add(buttonPanel);

        next = new JButton("next"); // jumps all over the place, whatever

        next.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (fui.getMaxRounds() <= 0)
                {
                    fui.endGame();
                }
                else
                {
                    ask(fui.getQuestion());
                }
            }
        });
        this.add(next);

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
                final JButton source = (JButton) ae.getSource();
                source.setBackground(Color.RED);
                buttons[question.getSolution()].setBackground(Color.GREEN);

                for (JButton button : buttons)
                {
                    button.setEnabled(false);
                }
                next.setEnabled(true);

                fui.answer(question, source.getText());
            }
        };

        for (JButton button : buttons)
        {
            button.setOpaque(true);
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
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setText(question.getAnswers()[i]);
            buttons[i].setEnabled(true);
            next.setEnabled(false);
        }
    }
}

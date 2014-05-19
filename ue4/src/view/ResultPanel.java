package view;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import control.IStatisticController;
import control.IO;
public class ResultPanel extends JPanel
{
    private JLabel player, right, wrong;
    private JButton saveButton;
    public ResultPanel(FancyUI ui)
    {
        final FancyUI fui = ui;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.player = new JLabel();
        this.add(player);
        this.right = new JLabel();
        this.add(right);
        this.wrong = new JLabel();
        this.add(wrong);

        saveButton = new JButton("save");
        saveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae){
                JFileChooser chooser = new JFileChooser();
                String filename = player.getText() + "_" + System.currentTimeMillis();
                chooser.setSelectedFile(new File(filename));
                chooser.showSaveDialog(null);
                File file = chooser.getSelectedFile();
                if(file == null){
                    return;
                }
                System.out.println(file.getAbsolutePath());
                IO.saveResultAs(file.getAbsolutePath(), fui.getController(), player.getText());
                fui.setup(); // start new game
            }
        });
        this.add(saveButton);

    }
    public void display(String player, IStatisticController controller)
    // gets called by the controlling FancyUI
    {
        this.player.setText("Player: " + player);
        this.right.setText("Right Answers: " + controller.getRightAnswers());
        this.wrong.setText("Wrong Answers: " + controller.getWrongAnswers());
    }

}
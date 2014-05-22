package view;
import javax.swing.JButton;

public class Pit extends JButton
{
    private int id;

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public Pit(int id)
    {
        super();
        this.id = id;
    }
    public Pit()
    {
        this(0);
    }

}

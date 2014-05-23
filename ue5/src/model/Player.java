package model;

public class Player
{
    public static final int ONE = 1;
    public static final int TWO = 2;

    private String name;
    private PlayerType type;
    private int id;

    public Player(String name, int id, PlayerType type)
    {
        this.type = type;
        this.name = name;
        this.id = id;
    }
    public Player(String name, int id)
    {
        this(name, id, PlayerType.HUMAN);
    }

    public String getName()
    {
        return name;
    }

    public PlayerType getType()
    {
        return type;
    }

    public int getKalaha()
    {
        if (id == ONE)
        {
            return 6;
        }
        if (id == TWO)
        {
            return 13;
        }
        return -1;
    }

    public int[] getPits()
    {
        if (id == ONE)
        {
            return new int[]{0,1,2,3,4,5};
        }
        if (id == TWO)
        {
            return new int[]{7,8,9,10,11,12};
        }
        return new int[]{-1};
    }
}

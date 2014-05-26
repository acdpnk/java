package model;

public class AI extends Player
{
    private static final int START_DEPTH = 5;
    private int move = -1;

    public AI(int id)
    {
        super("Morgan", id, PlayerType.AI);
    }

    public AI(String name, int id)
    {
        super(name, id, PlayerType.AI);
    }

    public int pickMove(Board board)
    {
        move = -1;
        alphaBeta(board, START_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(getName() + ": " + move);
        return move;
    }

    private int alphaBeta(Board board, int depth, int alpha, int beta)
    {
        if (depth == 0 || board.endReached())
        {
            return evaluateBoard(board);
        }
        if (board.getActivePlayer() == this)
        {
            int maxValue = alpha;
            for (int pit : this.getPits())
            {
                if (board.getSeeds(pit) == 0)
                {
                    // not a valid move
                    continue;
                }
                else
                {
                    Board clonedBoard = board.clone();
                    clonedBoard.executeMove(pit);
                    int value = alphaBeta(clonedBoard, depth-1,
                                          maxValue, beta);
                    if (value > maxValue)
                    {
                        maxValue = value;
                        if (maxValue > beta)
                        {
                            break;
                        }
                        if (depth == START_DEPTH)
                        {
                            move = pit;
                        }
                    }
                }
            }
            return maxValue;
        }
        else
        {
            int minValue = beta;
            for (int pit : board.getOpponent(this).getPits())
            {
                if (board.getSeeds(pit) == 0)
                {
                    // not a valid move
                    continue;
                }
                else
                {
                    Board clonedBoard = board.clone();
                    clonedBoard.executeMove(pit);
                    int value = alphaBeta(clonedBoard, depth-1,
                                          alpha, minValue);
                    if (value < minValue)
                    {
                        minValue = value;
                        if (minValue <= alpha)
                        {
                            break;
                        }
                    }
                }
            }
            return minValue;
        }
    }

    private int evaluateBoard(Board board)
    {
        // very naïve implementation, will improve if there's time and
        // motivation left before assignment is due.
        return board.getSeeds(getKalaha())
               - board.getSeeds(board.getOpponent(this).getKalaha());
    }
}

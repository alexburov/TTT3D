import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Alex on 11/21/14.
 */
public class Board
{
    private State[][][] cells = new State[4][4][4];

    public boolean playerWon = false;

    public void printBoard()
    {
        int level;
        int row;
        for (int i = 0; i < cells.length; i++)
        {
            level = cells.length-1-i;
            for (int j = 0; j < cells[i].length;j++)
            {
                row = cells.length-1-j;
                for (int t = j; t < cells[i].length;t++)
                {
                    System.out.print(" ");

                }
                System.out.printf("%d%d  ",level,row);
                for (int k = 0; k < cells[i][j].length;k++)
                {
                    System.out.print(cells[i][j][k]+ " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.print("   0 1 2 3");
        System.out.print("\n\n\n");
    }

    public void createEmptyBoard()
    {
        for (int i = 0; i < cells.length; i++)
        {
            for (int j = 0; j < cells[i].length;j++)
            {
                for (int k = 0; k < cells[i][j].length;k++)
                {
                    cells[i][j][k] = State.Empty;
                }
            }
        }
    }

    public State[][][] getCells()
    {
        return cells;
    }

    public void setComputerCell(int[] coords)
    {
        cells[coords[0]][coords[1]][coords[2]] = State.Computer;
        printBoard();
    }

    public void setPlayerCell(int[] coords)
    {
        cells[coords[0]][coords[1]][coords[2]] = State.Player;
        printBoard();
    }

    public boolean isEmpty(int[] coords)
    {
        return cells[coords[0]][coords[1]][coords[2]] == State.Empty;
    }

    public void scanWinningLines()
    {
        clearAllLists();
        int value = 0;
        for (int i = 0; i < lines.length; i++)
        {
            value = lines[i].getLineValue(cells);
            Line checkLine = lines[i];
            if (value == 20)
            {
                playerWon = true;
            }
            else if (value == 15)
            {
                addPlayerWinningLine(checkLine);
            }
            else if (value == 2)
            {
                addComputerHalfForksAndForks(checkLine);
            }

            else if (value == 3)
            {
                addComputerWinningLines(checkLine);
            }

            else if ((value/5) == 0)
            {
                addPlayerEmptyLine(checkLine);
            }

            else if (value == 10)
            {
                addPlayerHalfForksLine(checkLine);
            }
        }
    }

    public boolean arePlayerWinningLines()
    {
        return playerWinningLines.size() > 0;
    }

    public boolean areComputerWinningLines()
    {
        return computerWinningLines.size() > 0;
    }

    public boolean areComputerForkLines()
    {
        return computerForkLines.size() > 1;
    }

    public boolean arePlayerForkLines()
    {
        return playerHalfForkLines.size() > 1;
    }

    public boolean arePlayerEmptyLines()
    {
        return playerEmptyLines.size() > 0;
    }


    public Line getFirstComputerWinningLine()
    {
        return computerWinningLines.get(0);
    }

    public Line getFirstPlayerWinningLine()
    {
        return playerWinningLines.get(0);
    }

    public Line getComputerForkLine(int index)
    {
        return computerForkLines.get(index);
    }

    public Line getPlayerForkLine(int index)
    {
        return playerHalfForkLines.get(index);
    }

    public Line getRandomEmptyLine()
    {
        int random = new Random().nextInt(playerEmptyLines.size());
        Line emptyLine = playerEmptyLines.get(random);
        return emptyLine;
    }


    private void clearAllLists()
    {
        playerWinningLines.clear();
        computerWinningLines.clear();
        computerForkLines.clear();
        computerHalfForkLines.clear();
        playerHalfForkLines.clear();
        playerEmptyLines.clear();
    }

    private void addPlayerWinningLine(Line winningLine)
    {
        playerWinningLines.add(winningLine);
    }

    private void addComputerWinningLines(Line winningLine)
    {
        computerWinningLines.add(winningLine);
    }

    private void addComputerHalfForksAndForks(Line line)
    {
        computerHalfForkLines.add(line);
        if (computerHalfForkLines.size() > 1)
        {
            for (int j = 0; j < computerHalfForkLines.size(); j++)
            {
                if (Line.haveCommonPoint(cells,computerHalfForkLines.get(0),computerHalfForkLines.get(1)))
                {
                    computerForkLines.add(computerHalfForkLines.get(0));
                    computerForkLines.add(computerHalfForkLines.get(1));
                }
            }
        }
    }

    private void addPlayerEmptyLine(Line emptyLine)
    {
        playerEmptyLines.add(emptyLine);
    }

    private void addPlayerHalfForksLine(Line halfFork)
    {
        playerHalfForkLines.add(halfFork);
    }

    private ArrayList<Line> playerEmptyLines = new ArrayList<Line>();

    private ArrayList<Line> playerHalfForkLines = new ArrayList<Line>();

    private ArrayList<Line> playerWinningLines = new ArrayList<Line>();

    private ArrayList<Line> computerWinningLines = new ArrayList<Line>();

    private ArrayList<Line> computerForkLines = new ArrayList<Line>();

    private ArrayList<Line> computerHalfForkLines = new ArrayList<Line>();

    private final Line[] lines = {
            new Line(new int[][]{{0, 0, 0}, {0, 0, 1}, {0, 0, 2}, {0, 0, 3}}),  //lev 0; row 0   rows in each lev
            new Line(new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 1, 2}, {0, 1, 3}}),  //       row 1
            new Line(new int[][]{{0, 2, 0}, {0, 2, 1}, {0, 2, 2}, {0, 2, 3}}),  //       row 2
            new Line(new int[][]{{0, 3, 0}, {0, 3, 1}, {0, 3, 2}, {0, 3, 3}}),  //       row 3
            new Line(new int[][]{{1, 0, 0}, {1, 0, 1}, {1, 0, 2}, {1, 0, 3}}),  //lev 1; row 0
            new Line(new int[][]{{1, 1, 0}, {1, 1, 1}, {1, 1, 2}, {1, 1, 3}}),  //       row 1
            new Line(new int[][]{{1, 2, 0}, {1, 2, 1}, {1, 2, 2}, {1, 2, 3}}),  //       row 2
            new Line(new int[][]{{1, 3, 0}, {1, 3, 1}, {1, 3, 2}, {1, 3, 3}}),  //       row 3
            new Line(new int[][]{{2, 0, 0}, {2, 0, 1}, {2, 0, 2}, {2, 0, 3}}),  //lev 2; row 0
            new Line(new int[][]{{2, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}}),  //       row 1
            new Line(new int[][]{{2, 2, 0}, {2, 2, 1}, {2, 2, 2}, {2, 2, 3}}),  //       row 2
            new Line(new int[][]{{2, 3, 0}, {2, 3, 1}, {2, 3, 2}, {2, 3, 3}}),  //       row 3
            new Line(new int[][]{{3, 0, 0}, {3, 0, 1}, {3, 0, 2}, {3, 0, 3}}),  //lev 3; row 0
            new Line(new int[][]{{3, 1, 0}, {3, 1, 1}, {3, 1, 2}, {3, 1, 3}}),  //       row 1
            new Line(new int[][]{{3, 2, 0}, {3, 2, 1}, {3, 2, 2}, {3, 2, 3}}),  //       row 2
            new Line(new int[][]{{3, 3, 0}, {3, 3, 1}, {3, 3, 2}, {3, 3, 3}}),  //       row 3
            new Line(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 2, 0}, {0, 3, 0}}),  //lev 0; col 0   cols in each lev
            new Line(new int[][]{{0, 0, 1}, {0, 1, 1}, {0, 2, 1}, {0, 3, 1}}),  //       col 1
            new Line(new int[][]{{0, 0, 2}, {0, 1, 2}, {0, 2, 2}, {0, 3, 2}}),  //       col 2
            new Line(new int[][]{{0, 0, 3}, {0, 1, 3}, {0, 2, 3}, {0, 3, 3}}),  //       col 3
            new Line(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 2, 0}, {1, 3, 0}}),  //lev 1; col 0
            new Line(new int[][]{{1, 0, 1}, {1, 1, 1}, {1, 2, 1}, {1, 3, 1}}),  //       col 1
            new Line(new int[][]{{1, 0, 2}, {1, 1, 2}, {1, 2, 2}, {1, 3, 2}}),  //       col 2
            new Line(new int[][]{{1, 0, 3}, {1, 1, 3}, {1, 2, 3}, {1, 3, 3}}),  //       col 3
            new Line(new int[][]{{2, 0, 0}, {2, 1, 0}, {2, 2, 0}, {2, 3, 0}}),  //lev 2; col 0
            new Line(new int[][]{{2, 0, 1}, {2, 1, 1}, {2, 2, 1}, {2, 3, 1}}),  //       col 1
            new Line(new int[][]{{2, 0, 2}, {2, 1, 2}, {2, 2, 2}, {2, 3, 2}}),  //       col 2
            new Line(new int[][]{{2, 0, 3}, {2, 1, 3}, {2, 2, 3}, {2, 3, 3}}),  //       col 3
            new Line(new int[][]{{3, 0, 0}, {3, 1, 0}, {3, 2, 0}, {3, 3, 0}}),  //lev 3; col 0
            new Line(new int[][]{{3, 0, 1}, {3, 1, 1}, {3, 2, 1}, {3, 3, 1}}),  //       col 1
            new Line(new int[][]{{3, 0, 2}, {3, 1, 2}, {3, 2, 2}, {3, 3, 2}}),  //       col 2
            new Line(new int[][]{{3, 0, 3}, {3, 1, 3}, {3, 2, 3}, {3, 3, 3}}),  //       col 3
            new Line(new int[][]{{0, 0, 0}, {1, 0, 0}, {2, 0, 0}, {3, 0, 0}}),  //cols in vert plane in front
            new Line(new int[][]{{0, 0, 1}, {1, 0, 1}, {2, 0, 1}, {3, 0, 1}}),
            new Line(new int[][]{{0, 0, 2}, {1, 0, 2}, {2, 0, 2}, {3, 0, 2}}),
            new Line(new int[][]{{0, 0, 3}, {1, 0, 3}, {2, 0, 3}, {3, 0, 3}}),
            new Line(new int[][]{{0, 1, 0}, {1, 1, 0}, {2, 1, 0}, {3, 1, 0}}),  //cols in vert plane one back
            new Line(new int[][]{{0, 1, 1}, {1, 1, 1}, {2, 1, 1}, {3, 1, 1}}),
            new Line(new int[][]{{0, 1, 2}, {1, 1, 2}, {2, 1, 2}, {3, 1, 2}}),
            new Line(new int[][]{{0, 1, 3}, {1, 1, 3}, {2, 1, 3}, {3, 1, 3}}),
            new Line(new int[][]{{0, 2, 0}, {1, 2, 0}, {2, 2, 0}, {3, 2, 0}}),  //cols in vert plane two back
            new Line(new int[][]{{0, 2, 1}, {1, 2, 1}, {2, 2, 1}, {3, 2, 1}}),
            new Line(new int[][]{{0, 2, 2}, {1, 2, 2}, {2, 2, 2}, {3, 2, 2}}),
            new Line(new int[][]{{0, 2, 3}, {1, 2, 3}, {2, 2, 3}, {3, 2, 3}}),
            new Line(new int[][]{{0, 3, 0}, {1, 3, 0}, {2, 3, 0}, {3, 3, 0}}),  //cols in vert plane in rear
            new Line(new int[][]{{0, 3, 1}, {1, 3, 1}, {2, 3, 1}, {3, 3, 1}}),
            new Line(new int[][]{{0, 3, 2}, {1, 3, 2}, {2, 3, 2}, {3, 3, 2}}),
            new Line(new int[][]{{0, 3, 3}, {1, 3, 3}, {2, 3, 3}, {3, 3, 3}}),
            new Line(new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 2, 2}, {0, 3, 3}}),  //diags in lev 0
            new Line(new int[][]{{0, 3, 0}, {0, 2, 1}, {0, 1, 2}, {0, 0, 3}}),
            new Line(new int[][]{{1, 0, 0}, {1, 1, 1}, {1, 2, 2}, {1, 3, 3}}),  //diags in lev 1
            new Line(new int[][]{{1, 3, 0}, {1, 2, 1}, {1, 1, 2}, {1, 0, 3}}),
            new Line(new int[][]{{2, 0, 0}, {2, 1, 1}, {2, 2, 2}, {2, 3, 3}}),  //diags in lev 2
            new Line(new int[][]{{2, 3, 0}, {2, 2, 1}, {2, 1, 2}, {2, 0, 3}}),
            new Line(new int[][]{{3, 0, 0}, {3, 1, 1}, {3, 2, 2}, {3, 3, 3}}),  //diags in lev 3
            new Line(new int[][]{{3, 3, 0}, {3, 2, 1}, {3, 1, 2}, {3, 0, 3}}),
            new Line(new int[][]{{0, 0, 0}, {1, 0, 1}, {2, 0, 2}, {3, 0, 3}}),  //diags in vert plane in front
            new Line(new int[][]{{3, 0, 0}, {2, 0, 1}, {1, 0, 2}, {0, 0, 3}}),
            new Line(new int[][]{{0, 1, 0}, {1, 1, 1}, {2, 1, 2}, {3, 1, 3}}),  //diags in vert plane one back
            new Line(new int[][]{{3, 1, 0}, {2, 1, 1}, {1, 1, 2}, {0, 1, 3}}),
            new Line(new int[][]{{0, 2, 0}, {1, 2, 1}, {2, 2, 2}, {3, 2, 3}}),  //diags in vert plane two back
            new Line(new int[][]{{3, 2, 0}, {2, 2, 1}, {1, 2, 2}, {0, 2, 3}}),
            new Line(new int[][]{{0, 3, 0}, {1, 3, 1}, {2, 3, 2}, {3, 3, 3}}),  //diags in vert plane in rear
            new Line(new int[][]{{3, 3, 0}, {2, 3, 1}, {1, 3, 2}, {0, 3, 3}}),
            new Line(new int[][]{{0, 0, 0}, {1, 1, 0}, {2, 2, 0}, {3, 3, 0}}),  //diags left slice
            new Line(new int[][]{{3, 0, 0}, {2, 1, 0}, {1, 2, 0}, {0, 3, 0}}),
            new Line(new int[][]{{0, 0, 1}, {1, 1, 1}, {2, 2, 1}, {3, 3, 1}}),  //diags slice one to right
            new Line(new int[][]{{3, 0, 1}, {2, 1, 1}, {1, 2, 1}, {0, 3, 1}}),
            new Line(new int[][]{{0, 0, 2}, {1, 1, 2}, {2, 2, 2}, {3, 3, 2}}),  //diags slice two to right
            new Line(new int[][]{{3, 0, 2}, {2, 1, 2}, {1, 2, 2}, {0, 3, 2}}),
            new Line(new int[][]{{0, 0, 3}, {1, 1, 3}, {2, 2, 3}, {3, 3, 3}}),  //diags right slice
            new Line(new int[][]{{3, 0, 3}, {2, 1, 3}, {1, 2, 3}, {0, 3, 3}}),
            new Line(new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}, {3, 3, 3}}),  //cube vertex diags
            new Line(new int[][]{{3, 0, 0}, {2, 1, 1}, {1, 2, 2}, {0, 3, 3}}),
            new Line(new int[][]{{0, 3, 0}, {1, 2, 1}, {2, 1, 2}, {3, 0, 3}}),
            new Line(new int[][]{{3, 3, 0}, {2, 2, 1}, {1, 1, 2}, {0, 0, 3}})
    };
}

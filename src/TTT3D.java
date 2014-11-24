import java.util.Random;
import java.util.Scanner;

/**
 * Created by Alex on 11/21/14.
 */
public class TTT3D
{
    Board board = new Board();
    Scanner scanner = new Scanner(System.in);
    State[][][] cells = board.getCells();


    TTT3D()
    {
        board.createEmptyBoard();
        board.printBoard();
    }

    public void getPlayerMove()
    {
        System.out.println("Type your move as one three digit number(lrc):");
        String move = scanner.next();
        int[] coords = stringToCoords(move);
        if (board.isEmpty(coords))
        {
            board.setPlayerCell(coords);
        }
        else
        {
            System.out.println("This cell is occupied");
            getPlayerMove();
        }
    }

    public void getComputerMove()
    {
        board.scanWinningLines();
        if (board.playerWon)
        {
            printComputerDefeatMessage();
        }
        else if (board.areComputerWinningLines())
        {

            finishComputerWinningLine();
        }
        else if (board.arePlayerWinningLines())
        {
            blockPlayerWinningLine();
        }

        else if (board.areComputerForkLines())
        {
            createComputerForkLine();
        }

        else if (board.arePlayerForkLines())
        {
            tryToBlockPlayerForkLine();
        }

        else if (board.arePlayerEmptyLines())
        {
            makeRandomComputerMove();
        }
    }

    private void printComputerDefeatMessage()
    {
        System.out.println("Player won");
        System.exit(1);
    }

    private int[] stringToCoords(String string)
    {
        int value = Integer.parseInt(string);
        int level = 3 - value/100;
        int row = 3 - ((value % 100)/10);
        int column = value % 10;
        return new int[]{level,row,column};
    }

    private void finishComputerWinningLine()
    {
        Line winningLine = board.getFirstComputerWinningLine();

        for (int i = 0; i < winningLine.lineCoordinates.length;i++)
        {
            if (winningLine.getCoordinateValue(cells ,i) == 0)
            {
                board.setComputerCell(winningLine.lineCoordinates[i]);
                System.out.println("Computer wins again!");
                System.exit(1);
            }
        }
    }

    private void blockPlayerWinningLine()
    {
        Line playerWinningLine = board.getFirstPlayerWinningLine();

        for (int i = 0; i < playerWinningLine.lineCoordinates.length;i++)
        {
            if (playerWinningLine.getCoordinateValue(cells,i) == 0)
            {
                board.setComputerCell(playerWinningLine.lineCoordinates[i]);
                System.out.println("Computer blocked your line!");
                return;
            }
        }
    }

    private void createComputerForkLine()
    {
        Line forkLine1 = board.getComputerForkLine(0);
        Line forkLine2 = board.getComputerForkLine(1);
        int[] coords = forkLine1.getCommonPoint(forkLine2);
        if (Line.getCoordinateValue(cells,coords) == 0)
        {
            board.setComputerCell(coords);
            System.out.println("Computer created a fork!");
            return;
        }
    }

    private void makeRandomComputerMove()
    {
        Line emptyLine = board.getRandomEmptyLine();
        for (int i = 0; i < emptyLine.lineCoordinates.length;i++)
        {
            if (emptyLine.getCoordinateValue(cells,i) == 0)
            {
                board.setComputerCell(emptyLine.lineCoordinates[i]);
                System.out.println("Computer made a random move!");
                return;
            }
        }
    }

    private void tryToBlockPlayerForkLine()
    {
        Line halfForkLine1 = board.getPlayerForkLine(0);
        Line halfForkLine2 = board.getPlayerForkLine(1);
        int[] coords = halfForkLine1.getCommonPoint(halfForkLine2);
        if (coords != null && Line.getCoordinateValue(cells,coords) == 0)
        {
            board.setComputerCell(coords);
            System.out.println("Computer has blocked your potential fork!");
            return;
        }
        else if (board.arePlayerEmptyLines())
        {
            makeRandomComputerMove();
        }
    }
}

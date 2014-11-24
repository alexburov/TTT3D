import java.util.Arrays;

/**
 * Created by Alex on 11/21/14.
 */
public class Line
{
    public int[][] lineCoordinates = new int[4][3];

    Line(int[][] lineCoordinates)
    {
        this.lineCoordinates = lineCoordinates;
    }


    public int getCoordinateValue(State[][][] cells, int index)
    {
        int[] coords = this.lineCoordinates[index];
        return cells[coords[0]][coords[1]][coords[2]].getValue();
    }

    public int getLineValue(State[][][] cells)
    {
        int sum = 0;
        for (int i = 0; i < this.lineCoordinates.length; i++)
        {
            sum += this.getCoordinateValue(cells,i);
        }
        return sum;
    }

    public int[] getCommonPoint(Line line)
    {
        for (int i = 0; i < this.lineCoordinates.length; i++)
        {
            for (int j = 0; j < this.lineCoordinates.length; j++)
            {
                boolean result = Arrays.equals(this.lineCoordinates[i], line.lineCoordinates[j]);
                if (result)
                {
                    return lineCoordinates[i];
                }
            }
        }
        return null;
    }

    public static boolean haveCommonPoint(State[][][] cells,Line line1, Line line2)
    {
        for (int i = 0; i < line1.lineCoordinates.length; i++)
        {
            for (int j = 0; j < line1.lineCoordinates.length; j++)
            {
                boolean result = Arrays.equals(line1.lineCoordinates[i], line2.lineCoordinates[j]);
                if (result && line1.getCoordinateValue(cells,i) == 0)
                {
                    return result;
                }
            }
        }
        return false;
    }

    public static int getCoordinateValue(State[][][] cells,int[] coords)
    {
        return cells[coords[0]][coords[1]][coords[2]].getValue();
    }
}

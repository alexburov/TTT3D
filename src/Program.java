/**
 * Created by Alex on 11/21/14.
 */
public class Program
{
    public static void main(String[] args)
    {
        TTT3D game = new TTT3D();
        while (true)
        {
            game.getPlayerMove();
            game.getComputerMove();
        }
    }
}

import java.util.Scanner;

public class Main
{

    final static int ROWS = 3;
    final static int COLS = 3;
    static String board[][];


    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        String player = "X";
        board = new String[ROWS][COLS];
        int row = 0;
        int col = 0;
        int moveCnt = 0;
        boolean done = false;
        boolean playAgain = false;


    do {
        clearBoard();
        showBoard();
        moveCnt = 0;
        player = "X";
        done = false;

        do {

            do {

                // get Move for X, need a row and col
                System.out.println("Getting move from " + player);
                row = SafeInput.getRangedInt(in, "Enter your row", 1, 3);
                col = SafeInput.getRangedInt(in, "Enter your column", 1, 3);
                row--;
                col--;
            } while (!isValidMove(row, col));

            moveCnt++;
            recordMove(player, row, col);
            showBoard();

            if(isWin(player))
            {
                System.out.println(player + " wins!");
                done = true;
            }

            if(moveCnt == 9)
            {
                System.out.println("It's a tie!");
                done = true;
            }

            if (player.equals("X"))
                player = "O";
            else
                player = "X";

        } while (!done);

        playAgain = SafeInput.getYNConfirm(in, "Do you want to play again");

    } while (playAgain);


    }

    private static void clearBoard()
    {
        for(int r=0; r<ROWS; r++)
            for(int c=0; c<COLS; c++)
                board[r][c] = " ";

    }

    private static void showBoard()
    {
        for(int r=0; r<ROWS; r++)
        {
            System.out.print("|");
            for (int c = 0; c < COLS; c++)
                System.out.print(board[r][c] + " |");
            System.out.println();
        }

    }

    private static boolean isValidMove(int r, int c)
    {
        boolean retVal = false;
        /*
        if(r < 0 || r > ROWS || c < 0 || c > COLS)
        {
            System.out.println("Invalid move: " + r + " " + c);
        }
        */
        if(board[r][c].equals(" "))
            retVal = true;

        else {System.out.println("Invalid move: " + (r+1) + " " + (c+1));}

        return retVal;
    }

    private static void recordMove(String player, int r, int c)
    {
        board[r][c] = player;
    }

    private static boolean isWin(String player)
    {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;

    }

    private static boolean isRowWin(String player)
    {
        for(int r=0; r<ROWS; r++)
        {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                return true;
            }
        }
        return false;

    }

    private static boolean isColWin(String player)
    {
        for(int c = 0; c <COLS; c++)
        {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;

    }

    private static boolean isDiagonalWin(String player)
    {

            if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
            {
                return true;

            } else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
            {
                return true;
            }
        return false;

    }


}
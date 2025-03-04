import java.util.Scanner;
import java.util.Random;
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
}
public class CandyCrush {
    private static char[][] matrix;
    private static Scanner scan = new Scanner(System.in);
    private static Random generator = new Random();
    private static String colors = "RGB";
    private static int score = 0;
    private static int redscore = 0;
    private static int greenscore = 0;
    private static int bluescore = 0;
    private static int moveleft = -1;
    private static int gameMode=0;
    static String red = Colors.RED + "R" + Colors.RESET;
    static String green = Colors.GREEN + "G" + Colors.RESET;
    static String blue = Colors.BLUE + "B" + Colors.RESET;
    public static void initializeBoard()
    {
        System.out.println("Enter the size of the matrix: ");
        int a = scan.nextInt();
        int b = scan.nextInt();

        matrix = new char[a][b];

        for(int i = 0; i < a ; i++)
        {
            for(int j = 0 ; j < b ; j++ )
            {
                matrix[i][j] = colors.charAt(generator.nextInt(3));   //matrixi R G B ile rastgele doldurur.

                while (i >= 2 && matrix[i][j] == matrix[i - 1][j] && matrix[i][j] == matrix[i - 2][j] || j >= 2 && matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2])
                    matrix[i][j] = colors.charAt(generator.nextInt(3));
            }
        }
    }
    public static void printBoard(char[][] matrix)
    {

        for(int i = 0 ; i < matrix.length ; i++ )
        {
            for(int j = 0 ; j < matrix[i].length ; j++ )
            {
                if (j == matrix[i].length - 1 )
                {
                    if(matrix[i][j]=='R')
                        System.out.print("|" + red + "|");
                    else if(matrix[i][j]=='G')
                        System.out.print("|" + green + "|");
                    else if(matrix[i][j]=='B')
                        System.out.print("|" + blue + "|");

                }

                else
                {
                    if(matrix[i][j]=='R')
                        System.out.print("|" + red);
                    else if(matrix[i][j]=='G')
                        System.out.print("|" + green);
                    else if(matrix[i][j]=='B')
                        System.out.print("|" + blue);

                }
            }

            System.out.println();
        }
        System.out.println("-----------");
    }
    public static void swapCells()
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the cell: ");

        int x = scan.nextInt();
        int y = scan.nextInt();

        System.out.println("Enter the direction: ");

        String direction = scan.next();

        char temp = ' ';



        if(direction.equals("up"))
        {
            if(x==1)
                System.out.println("Invalid move!");
            else
            {
                temp = matrix[x-2][y-1];
                matrix[x-2][y-1]=matrix[x-1][y-1];
                matrix[x-1][y-1]=temp;

                moveleft--;

                if(!areThereAnyMatches())
                {
                    System.out.println("Invalid move!");
                    temp = matrix[x-2][y-1];
                    matrix[x-2][y-1]=matrix[x-1][y-1];
                    matrix[x-1][y-1]=temp;

                    moveleft++;
                }

            }
        }
        else if(direction.equals("down"))
        {
            if(x == matrix.length)
                System.out.println("Invalid move!");
            else
            {
                temp = matrix[x][y-1];
                matrix[x][y-1]=matrix[x-1][y-1];
                matrix[x-1][y-1]=temp;

                moveleft--;

                if(!areThereAnyMatches())
                {
                    System.out.println("Invalid move!");
                    temp = matrix[x][y-1];
                    matrix[x][y-1]=matrix[x-1][y-1];
                    matrix[x-1][y-1]=temp;

                    moveleft++;
                }

            }

        }

        else if(direction.equals("right"))
        {
            if(y==matrix[0].length)
                System.out.println("Invalid move!");
            else
            {
                temp = matrix[x-1][y];
                matrix[x-1][y]=matrix[x-1][y-1];
                matrix[x-1][y-1]=temp;

                moveleft--;

                if(!areThereAnyMatches())
                {
                    System.out.println("Invalid move!");
                    temp = matrix[x-1][y];
                    matrix[x-1][y]=matrix[x-1][y-1];
                    matrix[x-1][y-1]=temp;

                    moveleft++;
                }
            }

        }

        else if(direction.equals("left"))
        {
            if(y==1)
                System.out.println("Invalid move!");
            else
            {
                temp = matrix[x-1][y-2];
                matrix[x-1][y-2]=matrix[x-1][y-1];
                matrix[x-1][y-1]=temp;

                moveleft--;

                if(!areThereAnyMatches())
                {
                    System.out.println("Invalid move!");
                    temp = matrix[x-1][y-2];
                    matrix[x-1][y-2]=matrix[x-1][y-1];
                    matrix[x-1][y-1]=temp;

                    moveleft++;
                }
            }

        }

    }
    public static void checkForMatches()
    {
        for(int i = 0; i < matrix.length ; i++)
        {
            for(int j = 0 ; j < matrix[0].length ; j++ )
            {
                if(j >= 2 && matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2])//sadece yatay kontrol ve temizleme
                {
                    if(i==0)
                    {
                        if((matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2]) && matrix[i][j] == 'R')
                            redscore+=10;
                        else if((matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2]) && matrix[i][j] == 'G')
                            greenscore+=10;
                        else if(( matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2]) && matrix[i][j] == 'B')
                            bluescore+=10;

                        System.out.println("Clearing Board: ");
                        System.out.println("-----------");
                        matrix[0][j] = colors.charAt(generator.nextInt(3));
                        matrix[0][j-1] = colors.charAt(generator.nextInt(3));//ilk satırdaki 3 değer yan yana ise onları rastgele 3 değerle değişir
                        matrix[0][j-2] = colors.charAt(generator.nextInt(3));
                        printBoard(matrix);
                        score += 10;

                    }
                    else
                    {
                        if(( matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2]) && matrix[i][j] == 'R')
                            redscore+=10;
                        else if((matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2]) && matrix[i][j] == 'G')
                            greenscore+=10;
                        else if((matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2]) && matrix[i][j] == 'B')
                            bluescore+=10;

                        for(int k = i ; k > 0  ; k-- )      // eşleşen değeri siler. her bir satırı bir aşağı kaydırır
                        {
                            matrix[k][j] = matrix[k-1][j];
                            matrix[k][j-1] = matrix[k-1][j-1];
                            matrix[k][j-2] = matrix[k-1][j-2];
                        }
                        System.out.println("Clearing Board: ");
                        System.out.println("-----------");
                        matrix[0][j] = colors.charAt(generator.nextInt(3));     //ardından ilk satıra rastgele yeni bir değer atar.
                        matrix[0][j-1] = colors.charAt(generator.nextInt(3));
                        matrix[0][j-2] = colors.charAt(generator.nextInt(3));
                        printBoard(matrix);
                        score += 10;
                    }
                }

                if(i >= 2 && matrix[i][j] == matrix[i - 1][j] && matrix[i][j] == matrix[i - 2][j])  // dikey kontrol
                {
                    if((matrix[i][j] == matrix[i - 1][j] && matrix[i][j] == matrix[i - 2][j]) && matrix[i][j] == 'R')
                        redscore+=10;
                    else if((matrix[i][j] == matrix[i - 1][j] && matrix[i][j] == matrix[i - 2][j]) && matrix[i][j] == 'G')
                        greenscore+=10;
                    else if((matrix[i][j] == matrix[i - 1][j] && matrix[i][j] == matrix[i - 2][j]) && matrix[i][j] == 'B')
                        bluescore+=10;

                    for(int k = 0 ; k <= i ; k++)       // eşleşen dikey 3 lünun üstündeki her birini 3 birim aşağı kaydırır
                    {
                        if(k != i && k!= i-1 && k!= i-2)
                            matrix[k+3][j] = matrix[k][j];
                    }

                    System.out.println("Clearing Board: ");
                    System.out.println("-----------");
                    matrix[0][j]= colors.charAt(generator.nextInt(3));  //en üstte kalan boşluğu tekrar random değerlerle doldurur
                    matrix[1][j]= colors.charAt(generator.nextInt(3));
                    matrix[2][j]= colors.charAt(generator.nextInt(3));
                    printBoard(matrix);
                    score += 10;
                }
            }
        }
    }
    public static boolean isMovePossible()
    {

        for(int i = 0; i < matrix.length ; i++) //Bir eşleşmeye yol açabilecek herhangi bir hamlenin kalıp kalmadığını belirler.
        {
            for(int j = 0 ; j < matrix[0].length ; j++ )
            {
                if(j >= 2&& j<= matrix[0].length-3 && (matrix[i][j] == matrix[i][j-1] || matrix[i][j] == matrix[i][j+1]) && (matrix[i][j] == matrix[i][j-2] || matrix[i][j] == matrix[i][j+2]))
                    return true;
                if((j==0 && matrix[i][j]==matrix[i][j+1] && (matrix[i][j]==matrix[i][j+2] || matrix[i][j]==matrix[i][j+3])))
                    return true;
                if((j==matrix[0].length-1 && matrix[i][j]==matrix[i][j-1] && (matrix[i][j]==matrix[i][j-2] || matrix[i][j]==matrix[i][j-3])))
                    return true;
                if(i >= 2 && i<= matrix.length-3 && (matrix[i][j] == matrix[i-1][j] || matrix[i][j] == matrix[i+1][j]) && (matrix[i][j] == matrix[i-2][j] || matrix[i][j] == matrix[i+2][j]))
                    return true;
                if((i==0 && matrix[i][j]==matrix[i+1][j] && (matrix[i][j]==matrix[i+2][j] || matrix[i][j]==matrix[i+3][j])))
                    return true;
                if((i==matrix.length-1 && matrix[i][j]==matrix[i-1][j] && (matrix[i][j]==matrix[i-2][j] || matrix[i][j]==matrix[i-3][j])))
                    return true;
                if((j <= matrix[0].length-3 && i >= 1 && i<= matrix.length-2) && (matrix[i][j] == matrix[i][j+2] && (matrix[i][j] == matrix[i-1][j+1] || matrix[i][j] == matrix[i+1][j+1])))
                    return true;
                if((i <= matrix.length-3 && j >= 1 && j <= matrix[0].length-2) && (matrix[i][j] == matrix[i+2][j] && (matrix[i][j] == matrix[i+1][j-1] || matrix[i][j] == matrix[i+1][j+1])))
                    return true;
                if((i <= matrix.length-3 && j == 0) && (matrix[i][j] == matrix[i+2][j] && matrix[i][j] == matrix[i+1][j+1] ))
                    return true;
                if((i <= matrix.length-3 && j == matrix[0].length-1) && (matrix[i][j] == matrix[i+2][j] && matrix[i][j] == matrix[i+1][j-1] ))
                    return true;
                if((j <= matrix[0].length-3 && i == 0) && (matrix[i][j] == matrix[i][j+2] && matrix[i][j] == matrix[i+1][j+1] ))
                    return true;
                if((j <= matrix[0].length-3 && i == matrix.length-1) && (matrix[i][j] == matrix[i][j+2] && matrix[i][j] == matrix[i-1][j+1] ))
                    return true;

                    // iki tane yan yana aynı değer olduğunda kontrol ettim, ayrıca arada bir boşlukla aynı değer olduğunda kontrol ettim.
                    // örn:  RR_R ve R_R
                    //                R

            }
        }

        return false;
    }
    public static void shuffleBoard() //var olan boardu shuffle’lar
    {
        int rcount = 0;
        int gcount = 0;
        int bcount = 0;
        String rg = "RG";
        String rb = "RB";
        String gb = "GB";

        for(int i = 0 ; i < matrix.length ; i++)        // boarddaki toplam r g b sayısını buluyor
        {
            for(int j = 0 ; j < matrix[0].length ; j++)
            {
                if(matrix[i][j]=='R')
                    rcount++;
                else if(matrix[i][j]=='G')
                    gcount++;
                else if(matrix[i][j]=='B')
                    bcount++;

            }
        }

        for(int i = 0 ; i < matrix.length ; i++)
        {
            for(int j = 0 ; j < matrix[0].length ; j++)         // r g b yi rastgele yerleştirdikçe counter'dan bir eksiltiyor
            {
                if(rcount != 0 && gcount != 0 && bcount != 0)
                    matrix[i][j] = colors.charAt(generator.nextInt(3));
                else if(rcount == 0 && gcount != 0 && bcount != 0)
                    matrix[i][j] = gb.charAt(generator.nextInt(2));
                else if(gcount == 0 && rcount != 0 && bcount != 0)
                    matrix[i][j] = rb.charAt(generator.nextInt(2));
                else if(bcount == 0 && rcount != 0 && gcount != 0)
                    matrix[i][j] = rg.charAt(generator.nextInt(2));
                else if(rcount == 0 && gcount == 0 && bcount != 0)
                    matrix[i][j] = 'B';
                else if(rcount == 0 && bcount == 0 && gcount != 0)
                    matrix[i][j] = 'G';
                else if(bcount == 0 && gcount == 0 && rcount != 0)
                    matrix[i][j] = 'R';


                if(matrix[i][j]=='R')
                    rcount--;
                else if(matrix[i][j]=='G')
                    gcount--;
                else if(matrix[i][j]=='B')
                    bcount--;

            }
        }

    }
    public static boolean checkWinCondition(int gameMode)
    {
        if(gameMode == 1)
        {
            if(score >= 200 && moveleft >= 0)
                return true;
            else
                return false;

        }

        else if (gameMode == 2)
        {
            if(greenscore >= 100 && bluescore >= 100 && redscore >= 100 && moveleft >= 0)
                return true;
            else
                return false;

        }

        return false;
    }
    public static void printGameStatus()
    {
        if(gameMode == 1)
            System.out.println("You have collected "+score+ " points and you have "+moveleft+" moves left! ");
        else if(gameMode == 2)
            System.out.println("You have collected "+redscore+ " points for red, " +greenscore+ " points for green, "+bluescore+ " points for blue " + "and you have "+moveleft+" moves left! ");
        if(checkWinCondition(gameMode))
            System.out.println("Congragulations, You won the game! ");
        else if(!checkWinCondition(gameMode) && moveleft==0)
            System.out.println("You lost the game! ");
    }
    public static boolean areThereAnyMatches()
    {

        for(int i = 0; i < matrix.length ; i++)
        {
            for(int j = 0 ; j < matrix[0].length ; j++ )
            {

                if(j >= 2 && matrix[i][j] == matrix[i][j - 1] && matrix[i][j] == matrix[i][j - 2])// yatay kontrol
                    return true;
                if(i >= 2 && matrix[i][j] == matrix[i - 1][j] && matrix[i][j] == matrix[i - 2][j])  // dikey kontrol
                    return true;
            }
        }
        return false;
    }
    public static void main(String[] args)
    {

        System.out.println("Welcome to the game!");
        System.out.println("Enter s to start the game or q to quit:");
        String input = scan.next();


        if(input.equals("s"))
        {
            while (input.equals("s"))
            {
                gameMode = generator.nextInt(2) + 1; // gamemode ya 1 e yada 2 ye rastgele eşitlenir

                int tur = 1;

                if(gameMode == 1)
                {
                    moveleft = 15;

                    while (!(checkWinCondition(gameMode) ||  (!checkWinCondition(gameMode) && moveleft==0)))
                    {
                        if(tur == 1)
                        {
                            System.out.println("You have to collect 200 points in 15 moves!\n" + "Good luck!");
                            initializeBoard();
                            printBoard(matrix);
                        }

                        swapCells();
                        printBoard(matrix);

                        while(areThereAnyMatches())
                            checkForMatches();

                        while(!isMovePossible())
                        {
                            shuffleBoard();

                            while(areThereAnyMatches())
                                checkForMatches();
                        }

                        printGameStatus();
                        tur++;
                    }
                    moveleft = 15;
                    score = 0;
                    greenscore = 0;
                    redscore = 0;
                    bluescore = 0;
                }

                if(gameMode == 2)
                {
                    moveleft = 20;

                    while (!(checkWinCondition(gameMode) ||  (!checkWinCondition(gameMode) && moveleft==0)))
                    {
                        if(tur == 1)
                        {
                            System.out.println("You have to collect 100 points for each color in 20 moves!\n" + "Good luck!");
                            initializeBoard();
                            printBoard(matrix);
                        }

                        swapCells();
                        printBoard(matrix);

                        while(areThereAnyMatches())
                            checkForMatches();

                        while(!isMovePossible())
                        {
                            shuffleBoard();

                            while(areThereAnyMatches())
                                checkForMatches();
                        }

                        printGameStatus();
                        tur++;
                    }
                    moveleft = 20;
                    greenscore = 0;
                    redscore = 0;
                    bluescore = 0;
                    score = 0;
                }
                System.out.println("Enter s to start the game or q to quit:");
                input = scan.next();

                if(input.equals("q"))
                    System.out.println("Quitting...");
                else if(input.equals("s"))
                    ;
                else
                    System.out.println("Invalid input.");

            }
        }
        else if(input.equals("q"))
            System.out.println("Quitting...");
        else
            System.out.println("Invalid input.");
    }
}
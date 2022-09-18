package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       /* write your code here
             Test CAse !
          Scanner scanner = new Scanner(System.in);
          System.out.println("X O X");
          System.out.println("X O X");
          System.out.println("X O X");
          System.out.print("> ");
          String symbols = scanner.nextLine();
         char[][] gameGrid = new char[3][3];
          */

        String symbols = "         ";

        printBoard(symbols);




        /*          Test case 2

        if (xWins(symbols) && oWins(symbols)) {
              System.out.println("Impossible");
          } else if (isDraw(symbols)){
              System.out.println("Draw");
          } else if (xWins(symbols)) {
              System.out.println("X wins");
          } else if (oWins(symbols)) {
              System.out.println("O wins");
          } else if (gameNotFinishedOrImpossible(symbols)) {
              System.out.println("Game not finished");
          } else {
              System.out.println("Impossible");
          }

          */


        while (true) {
            symbols = userInput(symbols, 'X');
            if (isDraw(symbols)) {
                System.out.println("Draw!");
                break;
            } else if (xWins(symbols)) {
                System.out.println("X wins");
                break;
            } else if (oWins(symbols)) {
                System.out.println("O wins");
                break;
            }

            symbols = userInput(symbols, 'O');

            if (isDraw(symbols)) {
                System.out.println("Draw!");
                break;
            } else if (xWins(symbols)) {
                System.out.println("X wins");
                break;
            } else if (oWins(symbols)) {
                System.out.println("O wins");
                break;
            }
        }

    }

    static String userInput(String symbols, char ch) {
        System.out.print("> "); // asking user for input two cell
        String ans = "";
        Scanner scanner = new Scanner(System.in);
        boolean result = true;

        do {
            String str1 = scanner.nextLine(); // user will give input separated by the space.
            int in = str1.indexOf(" ");  // taking the index of the space.
            if (in < 0) {
                // if there is no space in the input user has entered then it's not a valid input.
                System.out.println("You should enter numbers!");
            } else {
                /* if there is space in the input store the part before the space inside the first index of another string array.
                 And the part after the space in the second index of the string array */

                String[] str = new String[2];
                str[0] = str1.substring(0, in);
                str[1] = str1.substring(in + 1, str1.length());

                if (str[1].isEmpty()) {
                    // after storing if the second element is empty it's not valid index.
                    System.out.println("You should enter numbers!");

                } else if (!((str[0].charAt(0) >= 48 && str[0].charAt(0) <= 57)
                        || (str[1].charAt(0) >= 48 && str[1].charAt(0) <= 57))) {
                    // checking if the first element is number or not.
                    // if it's not a number then it's not a valid input.
                    // user should enter again.
                    System.out.println("You should enter numbers!");
                } else {
                    /* if everything is correct it's checking for the length of the number.
                    if it's more than one that's not valid. User should enter again.
                    this is because we only want integer number lies between 1 and 3. */

                    if (str[0].length() > 1 || str[1].length() > 1) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        // if all the above checks are good we store the digit inside two integer variables.

                        int first = Integer.parseInt(str[0]) - 1;
                        int second = Integer.parseInt(str[1]) - 1;

                        if (first < 0 || first > 2 || second < 0 || second > 2) {

                            // if the digit is less than 0 or greater than 3 then the user should enter again.

                            System.out.println("Coordinates should be from 1 to 3!");

                        } else if (ifExist(symbols, first, second)) {
                            // if the cell value is already filled then user should enter again.

                            System.out.println("This cell is occupied! Choose another one!");
                        } else {
                            // if every check are correct we should update the string and display the grid.
                            ans = updateTheGrid(symbols, first, second, ch);
                            printBoard(ans);
                            return ans;
                        }
                    }
                }
            }

        } while (result);

        return ans;

    }

    static boolean ifExist(String str, int first, int second) {
        // checking if the cell entered by the user is already occupied or not.


        int index = 0;
        /* here I'm converting the two cells
           to only one index for simplicity.
           so that we can easily access the string.
        */
        if (first == 0) {
            index = second;
        } else if (first == 1) {
            index = 3 + second;
        } else if (first == 2) {
            index = 6 + second;
        }

        if (str.charAt(index) == 'X' || str.charAt(index) == 'O') {
            return true;
        } else {
            return false;
        }

    }

    static void printBoard(String symbols) {
        // printing the grid
        System.out.println("---------");
        System.out.println("| " + symbols.charAt(0) + " " + symbols.charAt(1) + " " + symbols.charAt(2) + " |");
        System.out.println("| " + symbols.charAt(3) + " " + symbols.charAt(4) + " " + symbols.charAt(5) + " |");
        System.out.println("| " + symbols.charAt(6) + " " + symbols.charAt(7) + " " + symbols.charAt(8) + " |");
        System.out.println("---------");
    }

    static String updateTheGrid(String str, int first, int second, char ch) {
        // update the grid according to the user input.
        int index = 0;
        if (first == 0) {
            index = 0 + second;
        } else if (first == 1) {
            index = 3 + second;
        } else if (first == 2) {
            index = 6 + second;
        }

        // modifying the existing string.
        str = str.substring(0, index) + ch + str.substring(index + 1, str.length());

        return str;
    }

    static boolean xWins(String symbols) {

         /* Checking every possible combination
            so that value x could win and return the result as
            true or false.
          */

        if (symbols.charAt(0) == symbols.charAt(1) && symbols.charAt(0) == symbols.charAt(2)) {
            if (symbols.charAt(0) == 'X')
                return true;
        } else if (symbols.charAt(3) == symbols.charAt(4) && symbols.charAt(3) == symbols.charAt(5)) {
            if (symbols.charAt(3) == 'X')
                return true;
        } else if (symbols.charAt(6) == symbols.charAt(7) && symbols.charAt(6) == symbols.charAt(8)) {
            if (symbols.charAt(6) == 'X')
                return true;
        } else if (symbols.charAt(0) == symbols.charAt(3) && symbols.charAt(0) == symbols.charAt(6)) {
            if (symbols.charAt(0) == 'X')
                return true;
        } else if (symbols.charAt(1) == symbols.charAt(4) && symbols.charAt(1) == symbols.charAt(7)) {
            if (symbols.charAt(1) == 'X')
                return true;
        } else if (symbols.charAt(2) == symbols.charAt(5) && symbols.charAt(2) == symbols.charAt(8)) {
            if (symbols.charAt(2) == 'X') {
                return true;
            }
        } else if (symbols.charAt(0) == symbols.charAt(4) && symbols.charAt(0) == symbols.charAt(8)) {
            if (symbols.charAt(0) == 'X') {
                return true;
            }

        } else if (symbols.charAt(2) == symbols.charAt(4) && symbols.charAt(2) == symbols.charAt(6)) {
            if (symbols.charAt(2) == 'X') {
                return true;
            }
        }

        return false;

    }

    static boolean oWins(String symbols) {

        /* Checking every possible combination
            so that value x could win and return the result as
            true or false.
          */

        if (symbols.charAt(0) == symbols.charAt(1) && symbols.charAt(0) == symbols.charAt(2)) {
            if (symbols.charAt(0) == 'O')
                return true;
        } else if (symbols.charAt(3) == symbols.charAt(4) && symbols.charAt(3) == symbols.charAt(5)) {
            if (symbols.charAt(3) == 'O')
                return true;
        } else if (symbols.charAt(6) == symbols.charAt(7) && symbols.charAt(6) == symbols.charAt(8)) {
            if (symbols.charAt(6) == 'O')
                return true;
        } else if (symbols.charAt(0) == symbols.charAt(3) && symbols.charAt(0) == symbols.charAt(6)) {
            if (symbols.charAt(0) == 'O')
                return true;
        } else if (symbols.charAt(1) == symbols.charAt(4) && symbols.charAt(1) == symbols.charAt(7)) {
            if (symbols.charAt(1) == 'O')
                return true;
        } else if (symbols.charAt(2) == symbols.charAt(5) && symbols.charAt(2) == symbols.charAt(8)) {
            if (symbols.charAt(2) == 'O') {
                return true;
            }
        } else if (symbols.charAt(0) == symbols.charAt(4) && symbols.charAt(0) == symbols.charAt(8)) {
            if (symbols.charAt(0) == 'O') {
                return true;
            }

        } else if (symbols.charAt(2) == symbols.charAt(4) && symbols.charAt(2) == symbols.charAt(6)) {
            if (symbols.charAt(2) == 'O') {
                return true;
            }
        }

        return false;

    }

    static boolean isDraw(String symbols) {
        /* Checking every possible combination
            so that game could be tied and
            return true or false accordingly
          */

        if (symbols.matches("[XO]+")) {
            if (!xWins(symbols) && !oWins(symbols)) {
                return true;
            }
        }
        return false;
    }

  /*  static boolean gameNotFinishedOrImpossible(String symbols) {
        int xOccurance = 0;
        int oOccurance = 0;
        if (!xWins(symbols) && !oWins(symbols) && !isDraw(symbols)) {
            for (int i = 0; i < symbols.length(); i++) {
                if (symbols.charAt(i) == 'X') {
                    xOccurance++;
                } else if (symbols.charAt(i) == 'O') {
                    oOccurance++;
                }
            }
        }

        if (Math.abs(oOccurance - xOccurance) > 1) {
            return false;
        }
        return true;
    } */

}

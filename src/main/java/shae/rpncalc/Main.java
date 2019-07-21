package shae.rpncalc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* Create calculator with 15 decimal points, smallest allowable to save on memory, and format precision of 10 */
        Calculator calculator = new Calculator(15, 10);

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter expressions in Reverse Polish Notation:\n% ");

        while(input.hasNextLine()) {
            String inputString = input.nextLine();
            try {
                System.out.println(calculator.calculate(cleanInput(inputString)));
                System.out.print("% ");
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String cleanInput(String userInput) throws IllegalArgumentException {
         /* Remove all non-operators, non-whitespace, and non digit chars */
         return userInput.replaceAll("[^\\^\\*\\+\\-\\'clear'\\'undo'\\'sqrt'\\d/\\s]", "");
    }
}

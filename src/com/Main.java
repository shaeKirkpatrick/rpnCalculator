package com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator(15, 10);

        Scanner input = new Scanner(System.in);

        while(input.hasNextLine()) {
            String inputString = input.nextLine();
            try {
                System.out.println(calculator.calculate(validateAndCleanInput(inputString)));
                System.out.print("% ");
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String validateAndCleanInput(String userInput) throws IllegalArgumentException {
        //remove all non-operators, non-whitespace, and non digit chars
         String cleanInput = userInput.replaceAll("[^\\^\\*\\+\\-\\'clear'\\'undo'\\'sqrt'\\d/\\s]", "");

         if (userInput.equals(cleanInput)){
             throw new IllegalArgumentException("Invalid input. Try again");
         }

         return cleanInput;
    }
}

package org.chiffres;

import java.util.Scanner;

class InputAssist {

    private Scanner input;

    InputAssist() {
        this.input = new Scanner(System.in);
    }

    static boolean isInteger(String input) {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch( Exception e)
        {
            return false;
        }
    }

    int getInteger() {
        String answer;
        answer = this.input.next().trim();
        while (!isInteger(answer)){
            System.out.println("Invalid input, try again: ");
            answer = this.input.next().trim();
        }
        return Integer.parseInt(answer);
    }

    boolean getBoolean() {
        String answer;
        answer = this.input.next().trim().toLowerCase();
        while(answer.charAt(0)!='y' && answer.charAt(0)!='n'){
            System.out.println("Invalid input, try again: ");
            answer = this.input.next().trim().toLowerCase();
        }
        return (answer.charAt(0)=='y');
    }

}

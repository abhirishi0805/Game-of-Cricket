package Others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Utility {
    public static String getUserInput(String prompt) {
        String userInput = null;
        print(prompt + " ");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            userInput = bufferedReader.readLine().trim();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return userInput;
    }

    public static void pauseExecution(int timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printAndNextLine(String message) {
        print(message + "\n");
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static String centerAlignString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}

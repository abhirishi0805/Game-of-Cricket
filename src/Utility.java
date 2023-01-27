import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Utility {
    public static String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader( new InputStreamReader(System.in));
            inputLine = is.readLine();
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }

    public static void pauseExecution(int timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

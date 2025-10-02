import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputNumber = new Scanner(System.in);
        String userInput;
        boolean yesorno = true;

        try {
            while (yesorno) {
                System.out.println("Geben Sie eine ganzzahlige Dezimalzahl ein (q to Quit): ");
                userInput = inputNumber.nextLine();


                if (userInput.equals("q")) {
                    System.exit(0);
                }

                boolean specialCase = false;

                if (userInput.equals("0")) {
                    System.out.println("Binärdarstellung: 0" );
                    specialCase = true;
                }

                if (specialCase == false) {
                    int inputFromUserInInt = Integer.parseInt(userInput);
                    String numberInBinary = "";

                    while (inputFromUserInInt > 0) {
                        int restFromInputDividedByTwo = inputFromUserInInt % 2;
                        numberInBinary = restFromInputDividedByTwo + numberInBinary;
                        inputFromUserInInt = inputFromUserInInt / 2;
                    }

                    System.out.println("Binärdarstellung: " + numberInBinary);
                }
            }
        } catch (Exception ex) {
            System.out.println("Fehler: Falsche Eingabe");
        }
    }
}

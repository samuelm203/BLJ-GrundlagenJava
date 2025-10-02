import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        try {
            while (true) {
                int zahlEins = 0;
                int zahlZwei = 0;
                String operator = "";

                System.out.println("Make your calculation (or press Q to quit): ");
                String calculation = input.nextLine();

                if (calculation.equals("Q") || calculation.equals("q")) {
                    System.out.println("The console will exit\n");
                    System.exit(0);
                }

                String[] rechnungInEinzelnTeilen = calculation.trim().split("\\s+");

                for (int i = 0; i < rechnungInEinzelnTeilen.length; i++) {
                    if (rechnungInEinzelnTeilen[i].equals("/")
                            || rechnungInEinzelnTeilen[i].equals("*")
                            || rechnungInEinzelnTeilen[i].equals("-")
                            || rechnungInEinzelnTeilen[i].equals("+")) {

                        operator = rechnungInEinzelnTeilen[i];
                    } else if (i == 0) {
                        zahlEins = Integer.parseInt(rechnungInEinzelnTeilen[0]);
                    } else {
                        zahlZwei = Integer.parseInt(rechnungInEinzelnTeilen[2]);
                    }
                }

                if (operator.equals("/")) {
                    System.out.println(zahlEins / zahlZwei);
                } else if (operator.equals("*")) {
                    System.out.println(zahlEins * zahlZwei);
                } else if (operator.equals("-")) {
                    System.out.println(zahlEins - zahlZwei);
                } else {
                    System.out.println(zahlEins + zahlZwei);
                }
            }
        } catch (Exception e) {
            System.out.println("Falsche Eingabe");
        }
    }
}

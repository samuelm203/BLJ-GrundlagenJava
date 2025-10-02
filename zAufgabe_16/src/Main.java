import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            Random zahlZwischen1Und100 = new Random(1-101);

            boolean spielen = true;
            int randomZahl = zahlZwischen1Und100.nextInt(101);
            int rateVersuche = 0;
            String weiterspielen = "";

            while(spielen){
                System.out.println("Gib eine Zahl zwischen 1 und 100 ein: ");
                int zahlGeraten = input.nextInt();

                if (zahlGeraten == randomZahl) {
                    System.out.println("Die Zahl stimmt! Du hast total " + rateVersuche + " Versuche benötigt. Noch einmal spielen? [y/n]");
                    weiterspielen = input.next();

                    if(weiterspielen.equals("y")){
                        spielen = true;
                        rateVersuche = 0;
                        randomZahl = zahlZwischen1Und100.nextInt(101);
                    } else if (weiterspielen.equals("n")) {
                        spielen = false;
                    }

                } else if (zahlGeraten > randomZahl)  {
                    rateVersuche++;
                    System.out.println("Zahl ist zu gross! Nächster Versuch: ");

                } else if (zahlGeraten < randomZahl) {
                    rateVersuche++;
                    System.out.println("Zahl ist zu klein! Nächster Versuch: ");
                }
            }
        } catch (Exception ex) {
            System.out.println("Fehler: Falsche Eingabe");
        }
    }
}
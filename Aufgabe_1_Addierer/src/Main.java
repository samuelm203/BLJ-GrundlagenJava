import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int zahlEins;
        int zahlZwei;

        System.out.println("Diese Programm berechnet die Summe von zwei Zahlen.");

        System.out.print("Geben Sie ihre erste Zahl ein: ");
        zahlEins = input.nextInt();

        System.out.print("Geben Sie ihre zweite Zahl ein: ");
        zahlZwei = input.nextInt();

        /* Option 1: neuer Int der die Summe ausgeben würde
        int summe = zahlEins + zahlZwei;
        System.out.println("Die Summe der beiden Zahlen beträgt:" + summe); */

        //Option 2: die Summe wird direkt in der Ausgabe ausgerechnet
        System.out.println("Die Summe der beiden Zahlen beträgt: " + (zahlEins + zahlZwei) );

    }
}
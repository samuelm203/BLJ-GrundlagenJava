import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int zahl;
        int quersumme = 0;

        System.out.println("Gib eine ganzzahlige Zahl ein: ");
        zahl = userInput.nextInt();



        System.out.println("Die Quersumme von " + zahl + " ist " + berechneQuersumme(zahl, quersumme) + ".");
    }

    static int berechneQuersumme(int zahl, int quersumme) {
        quersumme = 0;

        while (zahl != 0) {
            quersumme = quersumme + (zahl % 10);
            zahl = zahl / 10;
        }
        return quersumme;
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inputNumberMonth = new Scanner(System.in);
        byte NumberMonth = 0;

        String[] Months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "October", "November", "December"};

        /* Aufgabe ohne die Eingabe zu Validieren:
        System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 12 ein: ");
        NumberMonth = inputNumberMonth.nextByte();
        */

        try {
            System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 12 ein: ");
            NumberMonth = inputNumberMonth.nextByte();

            if (NumberMonth < 1 || NumberMonth > 12) {
                System.out.println("Die Zahl sollte zwischen 1 und 12 sein.");
                return;
            }

        }
        catch (NumberFormatException ex) {
            System.out.println("Die Eingabe ist ungültig, gib eine Zahl ein.");
            return;
        }
        catch (Exception ex) {
            System.out.println("Fehler beim Lesen der Zahl ein: ");
            return;
        }

        System.out.println("Dein Monat ist: "  + Months[NumberMonth - 1]);
    }
}
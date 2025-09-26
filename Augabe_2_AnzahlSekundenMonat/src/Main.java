import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inputMonatTage = new Scanner(System.in);

        System.out.println("Berechnung von Sekunden eines Monats in Abhängigkeit seiner Anzahl Tage");
        System.out.println("------------------------------------------------------------------------");

        byte tageDesMonats;

        /* Ohne Code Validation wäre es:
            System.out.println("Wie viele Tage hat der Monat, für den Sie die Sekundenzahl berechnen wollen?");
            String input = inputMonatTage.nextLine();
            tageDesMonats = Byte.parseByte(input);
        */

        // Zusatz; Mit Code Validation ist es:
        try {
            System.out.println("Wie viele Tage hat der Monat, für den Sie die Sekundenzahl berechnen wollen?");
            String input = inputMonatTage.nextLine();
            tageDesMonats = Byte.parseByte(input);

            if(tageDesMonats < 28 ||  tageDesMonats > 31 ) {
                System.out.println("Ungültige Eingabe! Ein Monat hat nur zwischen 28 und 31 Tagen.");
                return;
            }

            System.out.println("Ihre Eingabe " + tageDesMonats  + " ist gültig.");
        }
        catch (NumberFormatException ex) {
            System.out.println("Eingabefehler. Bitte geben Sie eine Zahl ein.");
            return;
        }
        catch (Exception ex) {
            System.out.println("Unbekannter Fehler: " + ex.toString());
            return;
        }

        //Die Anzahl der Sekunden anhand von Anzahl der Tage wird berechnet und ausgegeben
        int anzahlSekundenDesMonats = tageDesMonats * 24 * 60 * 60;

        System.out.print("Ein Monat mit " + tageDesMonats + " Tagen hat " + anzahlSekundenDesMonats + " Sekunden.");

    }
}
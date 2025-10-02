import java.util.Scanner;

public class Main {

    static boolean welchesJahr(int jahrInInt){
        if (jahrInInt >= 1582 ) {
            return jahrUeber1582(jahrInInt);
        } else if (jahrInInt > 0 && jahrInInt <= 1581) {
            return jahrUnter1582(jahrInInt);
        } else {
            System.out.println("Ungültige Eingabe");
            return false;
        }
    }

    static boolean jahrUeber1582(int jahrInInt) {
        if (jahrInInt % 4 == 0 && (jahrInInt % 100 != 0 || jahrInInt % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    static boolean jahrUnter1582(int jahrInInt) {
        return (jahrInInt % 4 == 0);
    }

    public static void main(String[] args) {

        boolean wiederholung = true;

        System.out.println("------------------------------------------------------------");
        System.out.println("Prüfen, ob es sich bei einem Jahr um ein Schaltjahr handelt.");
        System.out.println("------------------------------------------------------------");
        try{
            while (wiederholung) {
                Scanner input = new Scanner(System.in);

                System.out.print("Eingabe Jahr (q to quit): ");
                String jahr = input.nextLine();

                if (jahr.equalsIgnoreCase("q")) {
                    System.out.println("Konsolenprogramm wird beendet");
                    wiederholung = false;
                    System.exit(0);
                }

                int jahrInInt = Integer.parseInt(jahr);
                boolean schaltjahr = welchesJahr(jahrInInt);

                if (schaltjahr) {
                    System.out.println("Das Jahr " + jahr + " ist ein Schaltjahr.");
                } else {
                    System.out.println("Das Jahr " + jahr + " ist KEIN Schaltjahr.");
                }
            }
        } catch (Exception ex) {
            System.out.println("Fehler: Falsche Eingabe");
        }
    }
}

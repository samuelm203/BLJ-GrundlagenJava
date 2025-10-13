import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static class Kontakt {
        String vorname;
        String nachname;
        String email;

        Kontakt(String v, String n, String e) {
            vorname = v;
            nachname = n;
            email = e;
        }

        @Override
        public String toString() { // toString liefert schönes Ergebnis
            return vorname + " " + nachname + " (" + email + ")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Kontakt[] kontakte = new Kontakt[10000];
        int count = 0;

        System.out.println("*********************");
        System.out.println("* Kontakte erfassen *");
        System.out.println("*********************");

        while (true) {
            System.out.print("Vorname: ");
            String vorname = scanner.nextLine().trim(); // trim entfernt "Whitespaces"
            System.out.print("Nachname: ");
            String nachname = scanner.nextLine().trim();
            System.out.print("E-Mail: ");
            String email = scanner.nextLine().trim();

            kontakte[count] = new Kontakt(vorname, nachname, email);
            count++;

            System.out.print("Eingabe beenden(j/n) ");
            String antwort = scanner.nextLine().trim().toLowerCase();
            if (antwort.equals("j")) {
                break; // While-Schleife wird beendet
            }
            System.out.println();
        }

        Kontakt[] gespeicherteKontakte = Arrays.copyOf(kontakte, count);

        System.out.println("Zuerst Vorname[v] oder Nachname[n]?");
        String auswahl = scanner.nextLine().trim().toLowerCase();

        if (auswahl.equals("n")) {
            for (int i = 0; i < gespeicherteKontakte.length; i++) {
                System.out.println(gespeicherteKontakte[i].nachname + " " +  gespeicherteKontakte[i].vorname + " (" + gespeicherteKontakte[i].email + ")");
            }
        } else {
            for (int i = 0; i < gespeicherteKontakte.length; i++) {
                System.out.println(gespeicherteKontakte[i].vorname + " " + gespeicherteKontakte[i].nachname + " (" + gespeicherteKontakte[i].email + ")");
            }
        }
    }
}

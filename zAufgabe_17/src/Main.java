import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner geburtstagDatum = new Scanner(System.in);
            Instant start = Instant.now();

            System.out.println("Bitte gib ein Geburtsdatum ein (yyyy-mm-dd): ");
            String geburtdatum = geburtstagDatum.nextLine();

            LocalDate birthdate = LocalDate.parse(geburtdatum); // String wird in Datum umgewandelt: jahr-monat-tage
            LocalDate today = LocalDate.now(); // Das momentane Datum wird gespeichert

            Period period = Period.between(birthdate, today);

            int years = period.getYears();
            int months = years * 12 + period.getMonths();
            int days = (int) (months * 30.437 + period.getDays());
            int weeks = days / 7;

            System.out.println("Alter in Jahren: " + years);
            System.out.println("Alter in Monaten: " + months);
            System.out.println("Alter in Wochen: " + weeks);
            System.out.println("Alter in Tagen: " + days);
        } catch (Exception ex) {
            System.out.println("Fehler: Falsche Eingabe" + ex.getMessage());
        }
    }
}
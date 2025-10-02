import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static String[] ueberpruefen(String[] satzInEinzelnBuchstaben, String[] vokale) {
        String[] vokaleImSatz = new String[satzInEinzelnBuchstaben.length];

        for (int i = 0; i < vokale.length; i++) {
            for (int j = 0; j < satzInEinzelnBuchstaben.length; j++) {
                if (satzInEinzelnBuchstaben[j].equals(vokale[i])) {
                    vokaleImSatz[j] = satzInEinzelnBuchstaben[j];
                }
            }
        }
        return vokaleImSatz;
    }

    static void welcheVokale(String[] cleanedArray) {
        String[] vokale = {"a", "e", "i", "o", "u", "ä", "ö", "ü"};
        int[] zaehler = new int[vokale.length];

        for (String buchstabe : cleanedArray) {
            for (int i = 0; i < vokale.length; i++) {
                if (buchstabe.equals(vokale[i])) {
                    zaehler[i]++;
                }
            }
        }

        for (int i = 0; i < vokale.length; i++) {
            if (zaehler[i] > 0) {
                System.out.println("Der Buchstabe '" +vokale[i] + "' kommt " + zaehler[i] + " mal vor.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            String[] vokale = {"a", "e", "i", "o", "u", "ä", "ö", "ü"};
            Scanner input = new Scanner(System.in);

            System.out.println("Gib einen Satz ein: ");
            String satz = input.nextLine().toLowerCase();

            String[] satzInEinzelnBuchstaben = satz.split("");

            String[] alleVokaleImSatz = ueberpruefen(satzInEinzelnBuchstaben, vokale);

            String[] cleanedArray = Arrays.stream(alleVokaleImSatz)
                    .filter(Objects::nonNull)
                    .toArray(String[]::new); // entfernt Nulls

            System.out.println("Dein Text enthält " + cleanedArray.length + " Vokale.");
            welcheVokale(cleanedArray);
        } catch (Exception ex) {
            System.out.println("Fehler: " + ex.getMessage());
        }
    }
}

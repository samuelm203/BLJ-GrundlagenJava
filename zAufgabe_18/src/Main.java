import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static String[] ueberpruefen(String[] satzInEinzelnBuchstaben, String[] vokale) {

        int counterVokale = 0;
        String[] vokaleImSatz = new String[satzInEinzelnBuchstaben.length];

        for (int i = 0; i < vokale.length; i++) {
            for (int j = 0; j < satzInEinzelnBuchstaben.length; j++) {
                if (satzInEinzelnBuchstaben[j].equals(vokale[i])) {
                    counterVokale++;
                    vokaleImSatz[j] = satzInEinzelnBuchstaben[j];
                }
            }
        }

        return vokaleImSatz;
    }

    public static void main(String[] args) {

        String[] vokale = {"a", "e", "i","o", "u", "ä", "ö", "ü"};
        Scanner input = new Scanner(System.in);

        System.out.println("Gib einen Satz ein: ");
        String satz = input.nextLine();

        satz = satz.toLowerCase();

        String[] satzInEinzelnBuchstaben = satz.split("");

        ueberpruefen(satzInEinzelnBuchstaben, vokale);

        String[] alleVokaleImSatz = ueberpruefen(satzInEinzelnBuchstaben, vokale);

        String[] cleanedArray = Arrays.stream(alleVokaleImSatz).filter(Objects::nonNull).toArray(String[]::new); //entfernt alle Nulls aus dem Array

        System.out.println("Dein Text enthält " + cleanedArray.length + " Vokale.");

    }
}
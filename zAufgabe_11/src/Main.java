import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] berechneZahlen(int zahl1Input, int zahl2Input) {
        int counter = zahl2Input - zahl1Input + 1;
        int[] alleZahlenVonZahl1BisZahl2 = new int[counter];

        for (int i = 0; i < counter; i++) {
            alleZahlenVonZahl1BisZahl2[i] = zahl1Input + i;
        }

        return alleZahlenVonZahl1BisZahl2;
    }

    static int[] berechneQuersumme(int[] allNumbers) {
        int laengeArray = allNumbers.length;
        int[] Quersummen = new int[laengeArray];

        for (int i = 0; i < laengeArray; i++) {
            int quersumme = 0;
            int zahl = allNumbers[i];

            while (zahl != 0) {
                quersumme += (zahl % 10);
                zahl /= 10;
            }

            Quersummen[i] = quersumme;
        }

        return Quersummen;
    }

    static boolean[] berechneDivision(int[] allNumbers, int[] allQuersumme) {
        int counter = allNumbers.length;
        boolean[] teilbar = new boolean[counter];

        for (int i = 0; i < counter; i++) {
            if (allQuersumme[i] != 0 && allNumbers[i] % allQuersumme[i] == 0) {
                teilbar[i] = true;
            }
        }
        return teilbar;
    }

    public static void main(String[] args) {
        Scanner inputNumbers = new Scanner(System.in);
        int zahl1Input;
        int zahl2Input;

        System.out.println("Gib die erste Zahl ein: ");
        zahl1Input = inputNumbers.nextInt();

        System.out.println("Gib die zweite Zahl ein:");
        zahl2Input = inputNumbers.nextInt();

        int[] allNumbers = berechneZahlen(zahl1Input, zahl2Input);
        int[] allQuersumme = berechneQuersumme(allNumbers);
        boolean[] teilbar = berechneDivision(allNumbers, allQuersumme);

        System.out.println("Zahl\tQuersumme\tZahl/Quersumme");

        for (int i = 0; i < allNumbers.length; i++) {
            if (teilbar[i]) {
                int division = allNumbers[i] / allQuersumme[i];
                System.out.println(allNumbers[i] + "\t\t" + allQuersumme[i] + "\t\t\t" + division);
            }
        }
    }
}

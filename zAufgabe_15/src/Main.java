import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Wie breit soll die Stamm sein?");
        int breiteStamm = input.nextInt();

        System.out.print("Wie hoch soll der Stamm sein?");
        int hoeheStamm = input.nextInt();

        System.out.print("Wie hoch soll die Krone sein?");
        int hoeheKrone  = input.nextInt();

        zeichneBaum(hoeheStamm, breiteStamm, hoeheKrone);
    }

    static void zeichneKrone(int hoeheKrone) {
        for (int i = 0; i < hoeheKrone; i++) {

            for (int j = 0; j < hoeheKrone - i - 1; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < (2 * i + 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void zeichneStamm(int hoeheStamm, int breiteStamm, int hoeheKrone) {
        int gesamtBreite = 2 * hoeheKrone - 1;
        int counter = 0;

        int abstaende = (gesamtBreite - breiteStamm) / 2;

        while (counter < hoeheStamm) {
            counter++;

            for (int i = 0; i < abstaende; i++) {
                System.out.print(" ");
            }

            for (int j = 0; j < breiteStamm; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void zeichneBaum(int hoeheStamm, int breiteStamm, int hoeheKrone) {
        zeichneKrone(hoeheKrone);
        zeichneStamm(hoeheStamm, breiteStamm, hoeheKrone);
    }

}
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void zeichneLinie(int laengeLinie) {

        String[] ausgabe = new String[laengeLinie + 1];

        for (int i = 0; i <= laengeLinie; i++) {
            ausgabe[i] = " ";
            for (int d = i + 1; i + d <= laengeLinie; d++) {
                ausgabe[i + d] = "*";
            }
            for (int c = 0; i - c != 0; c++) {
                ausgabe[c] = "*";
            }

            // StringBuilder löscht alle "," und "[]" raus. Braucht es eigentlich nicht, ist nur um es anschaulicher zu machen
            StringBuilder sb = new StringBuilder();
            for (String s : ausgabe) {
                if (s != null) {
                    sb.append(s);
                }
            }

            System.out.println(sb.toString());
        }
    }


        public static void main (String[]args){

            Scanner input = new Scanner(System.in);

            System.out.println("Wie lange soll die Linie sein?");
            System.out.print("Deine Eingabe: ");

            int laengeLinie = input.nextInt();

            if (laengeLinie == 0) {
                System.out.println("Ungültige Eingabe");
                System.exit(0);
            } else {
                zeichneLinie(laengeLinie);
            }
        }
    }

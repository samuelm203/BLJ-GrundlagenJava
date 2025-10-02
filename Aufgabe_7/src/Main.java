public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Zahlen zwischen 1 und 30, die durch 5 und/oder 3, ohne Rest teilbar sind:");

            int i = 1;
            int ohneRestDurchDrei;
            int ohneRestDurchFuenf;

            while (i <= 30) {
                ohneRestDurchDrei = i % 3;
                ohneRestDurchFuenf = i % 5;

                if (i == 30) {
                    System.out.println(i);
                    return;
                }

                if (ohneRestDurchDrei == 0 || ohneRestDurchFuenf == 0) {
                    System.out.print(i + ", ");
                }

                i++;
            }
        } catch (Exception ex) {
            System.out.println("Fehler: Falsche Eingabe");
        }
    }
}
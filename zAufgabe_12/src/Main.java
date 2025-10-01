import java.util.Scanner;

public class Main {

    static int[] sumUp(int[] inputArray) {

        int[] result = new int[inputArray.length];
        int counter = inputArray.length;

        result[0] = inputArray[0];

        for (int i = 1; i < counter; i++) {
            result[i] = inputArray[i] + result[i - 1];
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------");
        System.out.println("Zahlen aufsummieren");
        System.out.println("-------------------");

        System.out.println("Geben Sie die zu summierenden Ganzzahlen mit Komma getrennt ein: ");
        String input = scanner.nextLine();

        String[] inputArray = input.split(",");

        int[] numbers = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numbers[i] = Integer.parseInt(inputArray[i].trim());
        }

        sumUp(numbers);

        int[] result = sumUp(numbers);

        for (int i = 0; i < numbers.length; i++) {
                System.out.print("[" + (i) + "] --> " + result[i] + "\t ");
        }
    }
}

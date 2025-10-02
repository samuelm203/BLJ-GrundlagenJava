import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Main {
    public static void main(String[] args) throws IOException {
        int counter = 0;

        while (true) {
            Scanner hallo = new Scanner(System.in);

            // API URL
            URL url = new URL("https://witzapi.de/api/joke/");
            InputStream input = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String jsonData = reader.readLine();

            // JSON-Array parsen
            JSONArray obj = (JSONArray) JSONValue.parse(jsonData);

            // Erstes Element (das Witz-Objekt) holen
            JSONObject jokeObj = (JSONObject) obj.get(0);

            // "text" Feld auslesen
            String joke = (String) jokeObj.get("text");
            if (counter == 0) {
                System.out.println("---------------------------------------------------");
                System.out.println("Witz: ");
                System.out.println(joke);
                System.out.println("---------------------------------------------------");
            } else {
                System.out.println("---------------------------------------------------");
                System.out.println(joke);
                System.out.println("---------------------------------------------------");
            }


            System.out.println("\nNächsten Witz holen? j/n");
            String eingabe = hallo.nextLine();

            if (eingabe.equalsIgnoreCase("j")) {
                System.out.println("Dann hol dir noch einen 😄");
                counter++;
            } else {
                System.out.println("Okay, bis zum nächsten Mal!");
                System.exit(0);
            }
        }

    }
}

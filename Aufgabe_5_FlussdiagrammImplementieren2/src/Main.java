import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inputInKm = new Scanner(System.in);
        short userEingabeAnzahlKM = 0;
        String userEingabeBereitOderNicht;

        try{
            System.out.println("Wie viele Kilometer möchtest du rennen?");
            userEingabeAnzahlKM = inputInKm.nextShort();

            if(userEingabeAnzahlKM  > 42){
                System.out.println("Das schaffst du nicht!");
                return;
            }
        }
        catch(java.util.InputMismatchException ex) {
            System.out.println("Gib eine Zahl ein.");
        }
        catch (Exception ex) {
            System.out.println("Fehler beim Lesen der Zahl ein!");
        }

        userEingabeBereitOderNicht = inputInKm.nextLine();

        int userEingabeAnzahlInRunden = userEingabeAnzahlKM * 1000 / 400;

        System.out.println("Das sind " + userEingabeAnzahlInRunden + " Runden. Bereit für den Lauf? [Y/n]");
        userEingabeBereitOderNicht = inputInKm.nextLine();

        if(!(userEingabeBereitOderNicht.equals("Y") ||  userEingabeBereitOderNicht.equals("N"))){
            System.out.println("Du solltest [Y] oder [n] eingeben.");
            return;
        }

        if (userEingabeBereitOderNicht.equals("N")) {
            System.out.println("Ende");
        }

        if (userEingabeBereitOderNicht.equals("Y")) {
            double i = 1;
            while (i <= userEingabeAnzahlInRunden) {
                System.out.println("Du läufst Runde: " + i);
                i++;
            }
        }
    }
}

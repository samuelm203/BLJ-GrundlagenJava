import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner Comment = new Scanner(System.in);
        String[] forbiddenWords = { "viagra", "sex", "porno", "fick", "schlampe", "arsch" };
        String yourComment;
        int forbiddenWordCount = 0;
        boolean criticalComment = false;


        System.out.println("Dein Kommentar:");
        yourComment = Comment.nextLine();
        String[] lowerCaseYourCommentAndSplit = yourComment.toLowerCase().split(" ");

        for (String commentWord : lowerCaseYourCommentAndSplit) {
            for (String forbiddenWord : forbiddenWords) {
                if (commentWord.equals(forbiddenWord)) {
                    forbiddenWordCount++;
                    criticalComment = true;
                }
            }
        }

        if (!criticalComment) {
            System.out.println("Vielen Dank für deinen Kommentar.");
        }

        if (criticalComment) {
            System.out.println("Dein Kommentar enthält " + forbiddenWordCount + " verbotene Wörter");
            System.out.println("Er wird nicht veröffentlicht");
        }
    }
}
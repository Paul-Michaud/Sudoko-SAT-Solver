import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Grid {
    Case[][] cases = new Case[9][9];

    public Grid(String gridFile) {
        try(BufferedReader br = new BufferedReader(new FileReader(gridFile))) {
            int lineNumber = 0;
            for(String line; (line = br.readLine()) != null; ) {
                for (int colNumber = 0; colNumber < line.length(); colNumber++){
                    cases[lineNumber][colNumber] = new Case(lineNumber, colNumber, Character.getNumericValue(line.charAt(colNumber)));
                }
                lineNumber++;
                System.out.println("");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayGrid() {
        for (int ligne = 0; ligne <= 8; ligne++) {
            for (int colonne = 0; colonne <= 8; colonne++) {
                System.out.print(cases[ligne][colonne].getValue());
            }
            System.out.println("");
        }
    }



}

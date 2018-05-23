import java.io.*;

public class Grid {
    Case[][] cases = new Case[9][9];
    PrintWriter writer;


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

    public void initWriter() {
        try {
            this.writer = new PrintWriter("out.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        this.writer.close();
    }

    public void generateVars() {
        writer.print("def ");
        for(int i = 1; i<=9; i++) {
            for(int j = 1; j<=9; j++) {
                for(int k = 1; k<=9; k++) {
                   writer.print("l" + i + "c" + j + "v" + k + " ");
                }
            }
        }
        writer.println(";");
    }

    public void generateRules() {
        //caseRules();
        //this.writer.println(".");

        linesRules();
        this.writer.println(".");
        //columnRules();
        //blocRules();
    }

    public void caseRules() {
        //pour chaque case du plateau
        // ( 1 . ~2  . ~3 . ... . ~9) +
        // (~1 .  2  . ~3 . ... . ~9) +
        // (~1 . ~2  .  3 .~4 . ... . ~9) +
        // ...
        // (~1 . ~2 . ... . ~8 . 9)
        this.writer.print("(");
        for(int i = 1; i<=9; i++) {
            for(int j = 1; j<=9; j++) {
                //chaque case
                this.writer.print("(");
                for(int k = 1; k<=9; k++) {
                    this.writer.print("(");
                    for(int l = 1; l<=9; l++) {
                        if(l==k) this.writer.print("l"+i+"c"+j+"v"+l);
                        else this.writer.print("~"+"l"+i+"c"+j+"v"+l);
                        if(l<9) this.writer.print(".");
                    }
                    this.writer.println(")");
                    if(k<9)this.writer.print("+");
                }
                this.writer.println(")");
                if(j<9)this.writer.print(".");

            }
        }
        this.writer.println(")");
    }

    public void linesRules() {

        //première ligne pour 1
        //111 . ~211 .~311. ... ~911
        //...
        //~111. ~211. ~311. ... 911

        //seconde ligne pour 7
        //127 . ~227 .~327. ... ~927
        //...
        //~127. ~227. ~327. ... 927

        //dernière ligne pour 4
        //194 . ~294 .~394. ... ~994
        //...
        //~194. ~294. ~394. ... 994

        this.writer.print("(");
        for(int j = 1; j <= 9; j++) {
            this.writer.print("(");
            for(int k = 1; k<=9; k++) {
                this.writer.print("(");
                for(int l = 1; l<=9; l++) {
                    this.writer.print("(");
                    for(int m = 1; m<=9; m++) {
                        if(l==m) this.writer.print("l" + j + "c" + m + "v" + k);
                        else this.writer.print("~l" + j + "c" + m + "v" + k);
                        if(m<9) this.writer.print(".");
                    }
                    this.writer.println(")");
                    if(l<9) this.writer.print("+");
                }
                this.writer.print(")");
                if(k<9) this.writer.println(".");
            }
            this.writer.println(")");
            if(j<9) this.writer.println(".");
        }
        this.writer.print(")");

    }











    public void displayGrid() {
        for (int lines = 0; lines <= 8; lines++) {
            for (int colonne = 0; colonne <= 8; colonne++) {
                System.out.print(cases[lines][colonne].getValue());
            }
            System.out.println("");
        }
    }
}

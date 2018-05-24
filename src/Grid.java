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
        writeTrueValues();
        caseRules();
        linesRules();
        this.writer.println(".");
        colRules();
        this.writer.println(".");
        blocRules();
        this.writer.println(";");
    }

    public void writeTrueValues() {
        for (int lines = 0; lines <= 8; lines++) {
            for (int col = 0; col <= 8; col++) {
                if(cases[lines][col].getValue() != 0) {
                    this.writer.print("l"+(lines+1)+"c"+(col+1)+"v"+cases[lines][col].getValue()+".");
                }
            }
        }
    }


    public void caseRules() {

        for(int line = 1; line<=9; line++) {
            for(int col = 1; col<=9; col++) {
                for(int i = 1; i<=9; i++) {
                    for(int j = 1; j<=9; j++) {
                        if(i!=j) this.writer.println("(l"+line+"c"+col+"v"+i+" => ~l"+line+"c"+col+"v"+j+").");
                    }
                }
            }
        }
    }

    public void linesRules() {
        this.writer.print("(");
        for(int line = 1; line <= 9; line++) {
            this.writer.print("(");
            for(int val = 1; val<=9; val++) {
                this.writer.print("(");
                for(int col = 1; col<=9; col++) {
                    this.writer.print("l"+line+"c"+col+"v"+val);
                    if(col<9) this.writer.print("+");
                }
                this.writer.print(")");
                if(val<9) this.writer.print(".");
            }
            this.writer.println(")");
            if(line<9) this.writer.println(".");
        }
        this.writer.println(")");
    }

    public void colRules() {
        this.writer.print("(");
        for(int col = 1; col <= 9; col++) {
            this.writer.print("(");
            for(int val = 1; val<=9; val++) {
                this.writer.print("(");
                for(int line = 1; line<=9; line++) {
                    this.writer.print("l"+line+"c"+col+"v"+val);
                    if(line<9) this.writer.print("+");
                }
                this.writer.print(")");
                if(val<9) this.writer.print(".");
            }
            this.writer.println(")");
            if(col<9) this.writer.println(".");
        }
        this.writer.println(")");

    }

    public void blocRules() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {

                for(int val=1; val<=9;val++) {
                    this.writer.print("(");
                    for(int line = 1; line <= 3; line++) {

                        for(int col = 1; col <= 3; col++) {
                            this.writer.print("l"+(3 * i + line) + "c" + (3 * j + col) + "v"+val);
                            if(col*line!=9) this.writer.print("+");
                        }

                    }
                    this.writer.print(")");
                    if(val<9)this.writer.print(".");
                }
                if(i*j!=4)this.writer.println(".");
            }
        }
    }










    public void displayGrid() {
        for (int lines = 0; lines <= 8; lines++) {
            for (int col = 0; col <= 8; col++) {
                System.out.print(cases[lines][col].getValue());
            }
            System.out.println("");
        }
    }
}

import java.util.Arrays;

public class Case {
    boolean[] values = new boolean[9];
    private int lineNumber;
    private int colNumber;

    public Case(int lineNumber,int colNumber, int value) {
        //System.out.println(lineNumber+","+colNumber+","+value);
        this.lineNumber = lineNumber;
        this.colNumber = colNumber;
        Arrays.fill(values, Boolean.FALSE);

        if(value != 0) values[value-1] = true;
    }

    public int getValue() {
        for (int i = 0; i<=values.length-1; i++) {
            if(values[i]) return i+1;
        }
        return 0;
    }

}

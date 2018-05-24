public class Main {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("1 argument attendu");
            System.exit(1);
        }

        String file=args[0];
        Grid grid = new Grid(file);

        grid.initWriter();
        grid.generateVars();
        grid.generateRules();
        grid.closeWriter();

    }
}

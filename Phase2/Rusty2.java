import java.io.*;
public class Rusty2 {

    public static void main(String args[]) {
		InputStream input = System.in;

        if (args.length > 0) {
            try {
                input = new FileInputStream(args[0]);
            } catch (Exception e) {
                System.out.println("Cannot open file: " + args[0]);
                System.exit(1);
            }
        }

	Parser parser = new Parser(input);
	ASTNode exp;
    
	System.out.println("Rusty2 interpreter PL MEIC 2025/26\n");

	while (true) {
	    try {
		if (args.length == 0)
                    System.out.print("# ");
		exp = parser.Start();
		if (exp==null) System.exit(0);
			ASTType typ = exp.typecheck(new Environment<ASTType>());
			IValue v = exp.eval(new Environment<IValue>());
			System.out.println(v.toStr()+" : "+typ.toStr());
	    } catch (ParseException e) {
			System.out.println("Syntax Error.");
			if (args.length == 0)
                    parser.ReInit(System.in);
	    } catch (Exception e) {
			e.printStackTrace();
			if (args.length == 0)
                    parser.ReInit(System.in);
	    }
	}
    }
}

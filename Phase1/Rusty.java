public class Rusty {

    public static void main(String args[]) {
	Parser parser = new Parser(System.in);
	ASTNode exp;
    
	System.out.println("Rusty interpreter PL MEIC 2025/26 (v1.0)\n");

	while (true) {
	    try {
		System.out.print("# ");
		exp = parser.Start();
		if (exp==null) System.exit(0);
		
		IValue v = exp.eval(new Environment<IValue>());
		//if (!(exp instanceof ASTPrint) && !(exp instanceof ASTPrintln))
		System.out.println(v.toStr());
	    } catch (ParseException e) {
			System.out.println("Syntax Error.");
			parser.ReInit(System.in);
	    } catch (Exception e) {
			e.printStackTrace();
			parser.ReInit(System.in);
	    }
	}
    }
    
}

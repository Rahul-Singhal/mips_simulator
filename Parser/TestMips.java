
import org.antlr.v4.runtime.*;

public class TestMips {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRFileStream(args[0]);
        MipsLexer lexer = new MipsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MipsParser parser = new MipsParser(tokens);
        parser.setBuildParseTree(true);
        RuleContext tree = parser.text();
        tree.inspect(parser); // show in gui
        //tree.save(parser, "/tmp/R.ps"); // Generate postscript
        System.out.println(tree.toStringTree(parser));
    }
}
package MaxLexer;

import MaxLexer.Readers.*;

public class Test {
    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer();

        lexer.register(new WhiteSpaceReader());
        lexer.register(new WordReader());
        lexer.register(new CommentReader());
        lexer.register(new SymbolReader());
        lexer.register(new SingleLineCommentReader());

        Token[] tokens = lexer.tokenize("56 /* fdgfdgdf\n dfsdfd */\n if(1>0) print('100') //Foo\n dfsfsdg //\n");

        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i].value.replace(' ', '_'));
        }
        System.out.println("-------------------");
    }
}
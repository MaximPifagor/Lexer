package MaxLexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<IReadable> readers;

    public Lexer() {
        this.readers = new ArrayList<>();
    }

    public boolean register(IReadable reader) {
        return readers.add(reader);
    }

    public boolean unregister(IReadable reader) {
        return readers.remove(reader);
    }

    public Token[] tokenize(String str) throws Exception {
        List<Token> resultTokens = new ArrayList<Token>();
        int startIndex = 0;

        while (startIndex < str.length()) {
            String postfix = str.substring(startIndex);
            Token maxToken = new Token();

            for (IReadable reader : readers) {
                Token token = reader.read(postfix);

                if (token == null)
                    continue;

                if (maxToken.getLength() < token.getLength())
                    maxToken = token;
            }

            resultTokens.add(maxToken);
            startIndex += maxToken.getLength();
        }

        return resultTokens.toArray(new Token[0]);
    }
}

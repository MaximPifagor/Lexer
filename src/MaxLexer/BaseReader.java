package MaxLexer;

import javafx.util.Pair;

import java.util.HashMap;

public abstract class BaseReader<T> implements IReadable {

    private HashMap<Pair<Character, T>,T> statesMap = new HashMap<>();




    @Override
    public Token read(String input) throws Exception {
        reset();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char val = input.charAt(i);

            if(!nextState(val))
            {
                if(isFinalState())
                {
                    return builder.length() == 0 ? null : new Token(builder.toString());
                }
                else
                    return null;
            }

            builder.append(val);
        }

        if(isFinalState())
        {
            return builder.length() == 0 ? null : new Token(builder.toString());
        }
        else
            throw new Exception();

    }

    protected abstract void reset();

    protected abstract boolean nextState(char val);

    protected abstract boolean isFinalState();

}

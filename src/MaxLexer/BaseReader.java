package MaxLexer;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;

public abstract class BaseReader implements IReadable {

    private static HashMap statesMap = new HashMap();
    private static HashSet finalStates = new HashSet();

    protected static <T> void initMapping(Character val, T currentState, T nextState)
    {
        Pair<Character, T> key = new Pair<>(val, currentState);
        statesMap.put(key, nextState);
    }

    protected static <T> T getState(Character val, T currentState)
    {
        T nextState = (T)statesMap.get(new Pair<Character, T>(val, currentState));
        return nextState;
    }

    protected static <T> void initFinalState(T finalState)
    {
        finalStates.add(finalState);
    }

    protected static <T> boolean isFinalState(T state)
    {
        return finalStates.contains(state);
    }

    @Override
    public Token read(String input){
        reset();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++)
        {
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
            return null;

    }

    protected abstract void reset();

    protected abstract boolean nextState(char val);

    protected abstract boolean isFinalState();

}

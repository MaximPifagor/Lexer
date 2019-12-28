package MaxLexer.Readers;

import MaxLexer.BaseReader;
import javafx.util.Pair;

import java.util.HashMap;

public class NumberReader extends BaseReader<NumberReader.NumberReaderStates> {

    enum NumberReaderStates
    {
        START, NUMBER
    }

    private static HashMap<Pair<Character, NumberReaderStates>, NumberReaderStates> nextStateMap = new HashMap<>();

    static
    {
    }

    private static void put(Character currentChar, NumberReaderStates currentStare, NumberReaderStates nextState)
    {
        Pair<Character, NumberReaderStates> key = new Pair<>(currentChar, currentStare);
        nextStateMap.put(key, nextState);
    }

    private NumberReaderStates state;

    @Override
    protected boolean nextState(char val) {
        if(Character.isDigit(val))
        {
            state = NumberReaderStates.NUMBER;
            return true;
        }

        return false;
    }

    @Override
    protected boolean isFinalState() {
        return state == NumberReaderStates.NUMBER || state == NumberReaderStates.START;
    }

    @Override
    protected void reset() {
        state = NumberReaderStates.START;
    }
}

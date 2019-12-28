package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class NumberReader extends BaseReader {

    enum NumberReaderStates
    {
        START, NUMBER, COMMA
    }

    static
    {
        initMapping(',', NumberReaderStates.NUMBER, NumberReaderStates.COMMA);
        initFinalState(NumberReaderStates.START);
        initFinalState(NumberReaderStates.NUMBER);
    }

    private NumberReaderStates state;

    @Override
    protected boolean nextState(char val) {
        if(Character.isDigit(val))
        {
            state = NumberReaderStates.NUMBER;
            return true;
        }

        NumberReaderStates newState = getState(val, state);

        if(newState != null)
            state = newState;

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

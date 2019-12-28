package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class WordReader extends BaseReader {
    private enum WordReaderStates
    {
        START, LETTER
    }

    private WordReaderStates state;

    @Override
    protected boolean nextState(char val) {
        if(Character.isLetter(val))
        {
            state = WordReaderStates.LETTER;
            return true;
        }

        return false;
    }

    @Override
    protected boolean isFinalState() {
        return state == WordReaderStates.LETTER || state == WordReaderStates.START;
    }

    @Override
    protected void reset() {
        state = WordReaderStates.START;
    }
}

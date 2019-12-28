package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class WordReader extends BaseReader {

    enum WordReaderStates
    {
        START, LETTER
    }

    static
    {
        initFinalState(WordReaderStates.START);
        initFinalState(WordReaderStates.LETTER);
    }

    private WordReaderStates state;

    @Override
    protected boolean nextState(char val)
    {
        if(Character.isLetter(val))
        {
            state = WordReaderStates.LETTER;
            return true;
        }

        return false;
    }

    @Override
    protected boolean isFinalState()
    {
        return isFinalState(state);
    }

    @Override
    protected void reset()
    {
        state = WordReaderStates.START;
    }
}

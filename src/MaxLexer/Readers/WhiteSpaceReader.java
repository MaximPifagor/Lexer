package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class WhiteSpaceReader extends BaseReader {

    enum WhiteSpaceReaderStates
    {
        START, WHITESPACE
    }

    static
    {
        initFinalState(WhiteSpaceReaderStates.START);
        initFinalState(WhiteSpaceReaderStates.WHITESPACE);
    }

    private WhiteSpaceReaderStates state;

    @Override
    protected boolean nextState(char val) {
        if (Character.isWhitespace(val))
        {
            state = WhiteSpaceReaderStates.WHITESPACE;
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
        state = WhiteSpaceReaderStates.START;
    }
}

package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class WhiteSpaceReader extends BaseReader {
    private enum WhiteSpaceStates{
        START, WHITESPACE
    }

    private WhiteSpaceStates state;

    @Override
    protected boolean nextState(char val) {
        if (Character.isWhitespace(val))
        {
            state = WhiteSpaceStates.WHITESPACE;
            return true;
        }

        return false;
    }

    @Override
    protected boolean isFinalState() {
        return state == WhiteSpaceStates.WHITESPACE || state == WhiteSpaceStates.START;
    }

    @Override
    protected void reset() {
        state = WhiteSpaceStates.START;
    }
}

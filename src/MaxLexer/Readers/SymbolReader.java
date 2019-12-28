package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class SymbolReader extends BaseReader {

    enum SymbolReaderStates
    {
        START, SYMBOL
    }

    static
    {
        initFinalState(SymbolReaderStates.SYMBOL);
        initFinalState(SymbolReaderStates.START);
    }

    private SymbolReaderStates state;

    @Override
    protected boolean nextState(char val)
    {
        if(state == SymbolReaderStates.START)
        {
            state = SymbolReaderStates.SYMBOL;
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
        state = SymbolReaderStates.START;
    }
}

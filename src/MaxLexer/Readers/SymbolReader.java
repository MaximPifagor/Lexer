package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class SymbolReader extends BaseReader {
    private enum SymbolReaderEnum {
        START, SYMBOL
    }

    private SymbolReaderEnum state;

    public SymbolReader(){
    }

    @Override
    protected boolean nextState(char val) {
        if(state == SymbolReaderEnum.START){
            state = SymbolReaderEnum.SYMBOL;
            return true;
        }

        return false;
    }

    @Override
    protected boolean isFinalState() {
        return state == SymbolReaderEnum.SYMBOL;
    }

    @Override
    protected void reset() {
        state = SymbolReaderEnum.START;
    }
}

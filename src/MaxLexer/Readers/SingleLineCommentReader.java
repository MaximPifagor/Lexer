package MaxLexer.Readers;

import MaxLexer.BaseReader;
import javafx.util.Pair;

import java.util.HashMap;

public class SingleLineCommentReader extends BaseReader {

    enum SingleLineCommentReaderStates
    {
        START, FIRST_SLASH, SECOND_SLASH, COMMENT, END
    }

    static
    {
        initMapping('/', SingleLineCommentReaderStates.START, SingleLineCommentReaderStates.FIRST_SLASH);
        initMapping('/', SingleLineCommentReaderStates.FIRST_SLASH, SingleLineCommentReaderStates.SECOND_SLASH);
        initFinalState(SingleLineCommentReaderStates.END);
        initFinalState(SingleLineCommentReaderStates.START);
        initFinalState(SingleLineCommentReaderStates.COMMENT);
    }

    private SingleLineCommentReaderStates state;

    @Override
    protected boolean nextState(char val)
    {
        SingleLineCommentReaderStates newState = getState(val, state);

        if(newState != null)
        {
            state = newState;
            return true;
        }

        if(state == SingleLineCommentReaderStates.COMMENT || state == SingleLineCommentReaderStates.SECOND_SLASH)
        {
            if(val == '\n')
                state = SingleLineCommentReaderStates.END;
            else
                state = SingleLineCommentReaderStates.COMMENT;
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
        state = SingleLineCommentReaderStates.START;
    }
}

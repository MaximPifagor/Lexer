package MaxLexer.Readers;

import MaxLexer.BaseReader;
import javafx.util.Pair;

import java.util.HashMap;

public class SingleLineCommentReader extends BaseReader {
    private enum SingleLineCommentReaderStates{
        START, FIRST_SLASH, SECOND_SLASH, COMMENT, END
    }

    private static HashMap<Pair<Character, SingleLineCommentReaderStates>, SingleLineCommentReaderStates> nextStateMap = new HashMap<>();

    static
    {
        put('/', SingleLineCommentReaderStates.START, SingleLineCommentReaderStates.FIRST_SLASH);
        put('/', SingleLineCommentReaderStates.FIRST_SLASH, SingleLineCommentReaderStates.SECOND_SLASH);
    }

    private static void put(Character currentChar, SingleLineCommentReaderStates currentStare, SingleLineCommentReaderStates nextState)
    {
        Pair<Character, SingleLineCommentReaderStates> key = new Pair<>(currentChar, currentStare);
        nextStateMap.put(key, nextState);
    }

    private SingleLineCommentReaderStates state;

    public SingleLineCommentReader()
    {

    }

    @Override
    protected void reset()
    {
        state = SingleLineCommentReaderStates.START;
    }

    @Override
    protected boolean nextState(char val) {
        SingleLineCommentReaderStates newState = nextStateMap.get(new Pair<>(val, state));

        if(newState != null){
            state = newState;
            return true;
        }

        if(state == SingleLineCommentReaderStates.COMMENT || state == SingleLineCommentReaderStates.SECOND_SLASH){
            if(val == '\n')
                state = SingleLineCommentReaderStates.END;
            else
                state = SingleLineCommentReaderStates.COMMENT;
            return true;
        }

        return false;
    }

    @Override
    protected boolean isFinalState() {
        return state == SingleLineCommentReaderStates.END || state == SingleLineCommentReaderStates.START || state == SingleLineCommentReaderStates.COMMENT;
    }
}

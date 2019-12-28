package MaxLexer.Readers;

import MaxLexer.BaseReader;
import javafx.util.Pair;
import java.util.HashMap;

public class CommentReader extends BaseReader {
    private static enum CommentReaderEnum
    {
        START, COMMENT_BEGIN_SLASH, COMMENT_BEGIN_STAR, COMMENT, COMMENT_END_STAR, COMMENT_END_SLASH
    }

    private static HashMap<Pair<Character, CommentReaderEnum>, CommentReaderEnum> nextStateMap = new HashMap<Pair<Character, CommentReaderEnum>, CommentReaderEnum>();

    static
    {
        put('/', CommentReaderEnum.START, CommentReaderEnum.COMMENT_BEGIN_SLASH);
        put('*', CommentReaderEnum.COMMENT_BEGIN_SLASH, CommentReaderEnum.COMMENT);
        put('*', CommentReaderEnum.COMMENT, CommentReaderEnum.COMMENT_END_STAR);
        put('/', CommentReaderEnum.COMMENT_END_STAR, CommentReaderEnum.COMMENT_END_SLASH);
        put('/', CommentReaderEnum.COMMENT_END_SLASH, CommentReaderEnum.COMMENT_BEGIN_SLASH);
    }

    private static void put(Character currentChar, CommentReaderEnum currentStare, CommentReaderEnum nextState)
    {
        Pair<Character, CommentReaderEnum> key = new Pair<>(currentChar, currentStare);
        nextStateMap.put(key, nextState);
    }

    private CommentReaderEnum state;

    public CommentReader(){
    }

    @Override
    protected boolean nextState(char val) {
        CommentReaderEnum nextState = nextStateMap.get(new Pair<>(val, state));

        if (nextState != null){
            state = nextState;
            return true;
        }

        if(state == CommentReaderEnum.COMMENT_END_STAR)
        {
            state = CommentReaderEnum.COMMENT;
            return true;
        }

        if (isUnconditionalState(state)){
            return true;
        }

        return false;
    }

    private boolean isUnconditionalState(CommentReaderEnum state){
        return state == CommentReaderEnum.COMMENT;
    }

    @Override
    protected boolean isFinalState() {
        return state == CommentReaderEnum.COMMENT_END_SLASH || state == CommentReaderEnum.START;
    }

    @Override
    protected void reset() {
        state = CommentReaderEnum.START;
    }
}

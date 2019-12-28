package MaxLexer.Readers;

import MaxLexer.BaseReader;

public class CommentReader extends BaseReader {

    enum CommentReaderStates
    {
        START, COMMENT_BEGIN_SLASH, COMMENT_BEGIN_STAR, COMMENT, COMMENT_END_STAR, COMMENT_END_SLASH
    }

    static
    {
        initMapping('/', CommentReaderStates.START, CommentReaderStates.COMMENT_BEGIN_SLASH);
        initMapping('*', CommentReaderStates.COMMENT_BEGIN_SLASH, CommentReaderStates.COMMENT_BEGIN_STAR);
        initMapping('*', CommentReaderStates.COMMENT, CommentReaderStates.COMMENT_END_STAR);
        initMapping('/', CommentReaderStates.COMMENT_END_STAR, CommentReaderStates.COMMENT_END_SLASH);
        initMapping('/', CommentReaderStates.COMMENT_END_SLASH, CommentReaderStates.COMMENT_BEGIN_SLASH);
        initFinalState(CommentReaderStates.START);
        initFinalState(CommentReaderStates.COMMENT_END_SLASH);
    }

    private CommentReaderStates state;

    @Override
    protected boolean nextState(char val)
    {
        CommentReaderStates nextState = getState(val, state);

        if (nextState != null)
        {
            state = nextState;
            return true;
        }

        if(state == CommentReaderStates.COMMENT_END_STAR || state == CommentReaderStates.COMMENT_BEGIN_STAR)
        {
            state = CommentReaderStates.COMMENT;
            return true;
        }

        if (isUnconditionalState(state)){
            return true;
        }

        return false;
    }

    private boolean isUnconditionalState(CommentReaderStates state)
    {
        return state == CommentReaderStates.COMMENT;
    }

    @Override
    protected boolean isFinalState()
    {
        return isFinalState(state);
    }

    @Override
    protected void reset()
    {
        state = CommentReaderStates.START;
    }
}

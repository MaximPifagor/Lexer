package MaxLexer;

public class Token {
    public String value;

    public Token(){}

    public Token(String value) {
        this.value = value;
    }

    public int getLength()
    {
        return value == null ? 0 : value.length();
    }
}

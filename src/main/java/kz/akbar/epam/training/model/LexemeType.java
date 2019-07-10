package kz.akbar.epam.training.model;

public enum LexemeType {
    SYMBOL("\\S"),
    WORD("[\\w]+"),
    SENTENCE("[^\n.]+(\\.|\\.\\s)"),
    PARAGRAPH(".+\\."),
    ARTICLE("");

    private final String pattern;

    LexemeType(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}

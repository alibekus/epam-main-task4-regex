package kz.akbar.epam.training.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Content {
    Map<LexemeType, List<Lexeme>> lexemeMap;

    public Content() {
        this.lexemeMap = new TreeMap<>();
    }

    public void setLexemes(LexemeType type, List<Lexeme> lexemes) {
        lexemeMap.put(type, lexemes);
    }

    public Map<LexemeType, List<Lexeme>> getLexemeMap() {
        return lexemeMap;
    }

    public List<Lexeme> getLexemes(LexemeType type) {
        return lexemeMap.get(type);
    }
}

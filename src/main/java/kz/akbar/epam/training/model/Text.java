package kz.akbar.epam.training.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text {
    Map<LexemeType, List<Lexeme>> lexemeMap;

    public Text() {
        this.lexemeMap = new HashMap<LexemeType, List<Lexeme>>();
    }

    public void setLexemes(LexemeType type, List<Lexeme> lexemes){
        lexemeMap.put(type,lexemes);
    }

    public Map<LexemeType,List<Lexeme>> getLexemeMap() {
        return lexemeMap;
    }
}

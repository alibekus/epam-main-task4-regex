package kz.akbar.epam.training.processor;

import kz.akbar.epam.training.Main;
import kz.akbar.epam.training.model.Content;
import kz.akbar.epam.training.model.Lexeme;
import kz.akbar.epam.training.model.LexemeType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processor {
    private Processor nextProcessor;
    private LexemeType type;
    private String patternString;
    private Pattern pattern;
    private Matcher matcher;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());


    public Processor(LexemeType type) {
        this.type = type;
        this.patternString = type.getPattern();
        this.pattern = Pattern.compile(patternString);
    }

    public LexemeType getType() {
        return type;
    }

    public Processor nextProcessor(Processor processor) {
        this.nextProcessor = processor;
        return processor;
    }

    public Map<Lexeme, List<Lexeme>> processContent(Content content, LexemeType prevTypeToProcess) {
        LOGGER.info("Lexeme type to process: " + prevTypeToProcess);
        StringBuilder textBuilder = new StringBuilder();
        List<Lexeme> lexemesToProcess = content.getLexemeMap().get(prevTypeToProcess);
        List<Lexeme> resultLexemes = new ArrayList<>();
        Map<Lexeme, List<Lexeme>> lexemeResultListMap = new TreeMap<>();
        List<Lexeme> separateLexemes;
        if (type.equals(LexemeType.ARTICLE)) {
            processNext(content, type);
            return null;
        }
        for (Lexeme lexeme : lexemesToProcess) {
            matcher = pattern.matcher(lexeme.getContent());
            separateLexemes = new ArrayList<>();
            while (matcher.find()) {
                int matchStart = matcher.start();
                int matchEnd = matcher.end();
                textBuilder.append(lexeme.getPart(matchStart, matchEnd));
                resultLexemes.add(new Lexeme(textBuilder.toString()));
                separateLexemes.add(new Lexeme(textBuilder.toString()));
                textBuilder.delete(0, textBuilder.length());
            }
            lexemeResultListMap.put(lexeme,separateLexemes);
        }
        content.setLexemes(type, resultLexemes);
        processNext(content, type);
        return lexemeResultListMap;
    }

    protected void processNext(Content content, LexemeType type) {
        if (nextProcessor != null) {
            nextProcessor.processContent(content, type);
        }
    }
}

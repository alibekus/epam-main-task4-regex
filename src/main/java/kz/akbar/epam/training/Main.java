package kz.akbar.epam.training;

import kz.akbar.epam.training.model.Content;
import kz.akbar.epam.training.model.Lexeme;
import kz.akbar.epam.training.model.LexemeType;
import kz.akbar.epam.training.processor.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    static void sortParagraphSentence(Content content) {
        Processor processor = new SentenceProcessor();
        Map<Lexeme, List<Lexeme>> paragraphSentenceListMap = processor.processContent(content, LexemeType.PARAGRAPH);
        paragraphSentenceListMap.entrySet().stream().sorted((o1, o2) -> ((Integer)o1.getValue().size()).compareTo(o2.getValue().size())).forEach(lexemeListEntry -> {
            LOGGER.info("Amount of sentences: " + lexemeListEntry.getValue().size() + ": " + lexemeListEntry.getKey().getContent());
        });
    }

    static void sortSentenceWordByLength(Content content) {
        Processor processor = new WordProcessor();
        Map<Lexeme, List<Lexeme>> sentenceWordListMap = processor.processContent(content, LexemeType.SENTENCE);
        sentenceWordListMap.entrySet().stream().forEach(sentenceWordEntry -> {
            LOGGER.info("Sentence: " + sentenceWordEntry.getKey().getContent());
            LOGGER.info("Sentence word amount: " + sentenceWordEntry.getValue().size());
            sentenceWordEntry.getValue().stream().sorted(Comparator.comparing(Lexeme::getLength))
                    .forEach(word -> LOGGER.info(word.getContent()));
        });
    }

    static void sortParagraphBySentenceWordAmount(Content content) {
        Processor processor = new ParagraphProcessor().nextProcessor(new SentenceProcessor());
        Map<Lexeme, List<Lexeme>> paragraphSentenceListMap = processor.processContent(content, LexemeType.PARAGRAPH);
        processor = new SentenceProcessor().nextProcessor(new WordProcessor());
        Map<Lexeme, List<Lexeme>> sentenceWordListMap = processor.processContent(content, LexemeType.SENTENCE);
        Map<Lexeme,List<Lexeme>> paragraphWordListMap = new HashMap<>();
        paragraphSentenceListMap.entrySet().stream().forEach(paragraphSentenceEntry -> {
            List<Lexeme> paragraphWords = new ArrayList<>();
            paragraphSentenceEntry.getValue().stream().forEach(sentence -> {
                paragraphWords.addAll(sentenceWordListMap.get(sentence));
            });
            paragraphWordListMap.put(paragraphSentenceEntry.getKey(),paragraphWords);
        });
        paragraphWordListMap.entrySet().stream().sorted((entry1, entry2) -> ((Integer)entry1.getValue().size())
                .compareTo(entry2.getValue().size()))
                .forEach(paragraphWordEntry -> LOGGER.info(paragraphWordEntry.getKey().getContent()));
    }

    public static void main(String[] args) {
        /*if (args.length != 1) {
            System.err.println(
                    "Usage: java content file filename");
            System.exit(1);
        }*/
        BufferedReader br = null;
        String contentFilePath = "src/main/resources/text.txt";
        File contentFile = new File(contentFilePath);
        try {
            br = new BufferedReader(new FileReader(contentFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.info(args[0] + " not exist or can't read: " + e.getMessage());
            LOGGER.info(e.getMessage(), e);
        }
        try {
            LOGGER.info("Input file: " + contentFile.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Input content file not found! " + e.getMessage());
            LOGGER.info(e.getMessage(), e);
        }
        int charCode;
        StringBuilder textBuilder = new StringBuilder();
        try {
            while ((charCode = br.read()) != -1) {
                textBuilder.append((char) charCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Error with content line reading from file: " + e.getMessage());
            LOGGER.info(e.getMessage(), e);
        }
        Processor processor = new ArticleProcessor();
        processor.nextProcessor(new ParagraphProcessor()).nextProcessor(new SentenceProcessor())
                .nextProcessor(new WordProcessor()).nextProcessor(new SymbolProcessor());
        List<Lexeme> articleList = new ArrayList<Lexeme>();
        articleList.add(new Lexeme(textBuilder.toString()));
        Content content = new Content();
        content.setLexemes(LexemeType.ARTICLE, articleList);
        processor.processContent(content, LexemeType.ARTICLE);
        Map<LexemeType, List<Lexeme>> lexemeMap = content.getLexemeMap();
        lexemeMap.entrySet().stream().forEach(entry -> {
            LOGGER.info(entry.getKey() + ":");
            entry.getValue().stream().forEach(lexeme -> LOGGER.info(lexeme.getContent()));
        });
        LOGGER.info("Sorting paragraphs by sentence count:");
        sortParagraphSentence(content);
        LOGGER.info("Sorting sentences words by length:");
        sortSentenceWordByLength(content);
        LOGGER.info("Sorting paragraphs by words amount:");
        sortParagraphBySentenceWordAmount(content);
    }
}

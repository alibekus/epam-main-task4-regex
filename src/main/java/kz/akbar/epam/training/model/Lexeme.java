package kz.akbar.epam.training.model;

public class Lexeme implements Comparable<Lexeme> {
    private String content;
    private Integer length;

    public Lexeme(String content) {
        this.content = content;
        this.length = content.length();
    }

    public Integer getLength() {
        return content.length();
    }

    public String getContent() {
        return content;
    }

    public String getPart(int start, int end) {
        return content.substring(start,end);
    }

    public Character getChar(int i){
        return content.charAt(i);
    }

    @Override
    public int compareTo(Lexeme other) {
        return other.getContent().compareTo(content);
    }
}

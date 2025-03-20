package ru.kretsev.Exception;

public class HtmlParsingException extends RuntimeException{
    private final static String DEFAULT_MESSAGE = "Failed to parsing HTML file";
    public HtmlParsingException() {
    }

    public HtmlParsingException(String message) {
        super(message);
    }
}

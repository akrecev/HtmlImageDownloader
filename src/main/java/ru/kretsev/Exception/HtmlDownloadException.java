package ru.kretsev.Exception;

public class HtmlDownloadException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "Failed to load HTML file";

    public HtmlDownloadException() {
    }

    public HtmlDownloadException(String message) {
        super(message);
    }

    public HtmlDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}

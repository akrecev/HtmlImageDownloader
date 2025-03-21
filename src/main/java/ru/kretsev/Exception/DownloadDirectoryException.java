package ru.kretsev.Exception;

public class DownloadDirectoryException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "Failed to load image";

    public DownloadDirectoryException() {
    }

    public DownloadDirectoryException(String message) {
        super(message);
    }

    public DownloadDirectoryException(String message, Throwable cause) {
        super(message, cause);
    }
}

package ru.kretsev.Exception;

public class ImageDownloadException extends RuntimeException{
    private final static String DEFAULT_MESSAGE = "Failed to load image";
    public ImageDownloadException() {
    }

    public ImageDownloadException(String message) {
        super(message);
    }
}

package ru.kretsev.service;

import ru.kretsev.Exception.DownloadDirectoryException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ImageDownloader {
    public void downloadImages(List<String> imageUrls, String outputDir) throws DownloadDirectoryException {
        File directory = new File(outputDir);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new DownloadDirectoryException("Failed to create output directory: " + outputDir);
            }
        }

        int count = 0;
        for (String imageUrl : imageUrls) {
            try {
                URI uri = new URI(imageUrl);
                URL url = uri.toURL();
                String fileName = getFileNameFromUrl(imageUrl);
                String filePath = outputDir + File.separator + count + "_" + fileName;

                try (InputStream in = url.openStream()) {
                    Files.copy(in, Paths.get(filePath));
                }
                count++;
            } catch (java.net.URISyntaxException e) {
                System.err.println("Skipping invalid image URL syntax: " + imageUrl);
            } catch (IOException e) {
                System.err.println("Failed to download image, skipping: " + imageUrl);
            }
        }
    }

    private String getFileNameFromUrl(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        int paramIndex = fileName.indexOf('?');
        if (paramIndex != -1) {
            fileName = fileName.substring(0, paramIndex);
        }
        if (!fileName.matches(".*\\.(jpg|jpeg|png|gif|bmp)$")) {
            fileName += ".jpg"; // Добавление расширения по умолчанию
        }
        return fileName;
    }
}

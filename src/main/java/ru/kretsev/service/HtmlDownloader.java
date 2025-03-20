package ru.kretsev.service;

import ru.kretsev.Exception.HtmlDownloadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class HtmlDownloader {
    public String downloadHtml(String urlString) throws HtmlDownloadException {
        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL(); // Нормализация URL
            StringBuilder html = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    html.append(line).append("\n");
                }
            }
            return html.toString();
        } catch (java.net.URISyntaxException e) {
            throw new HtmlDownloadException("Invalid URL syntax: " + urlString);
        } catch (IOException e) {
            throw new HtmlDownloadException("Failed to download HTML from " + urlString + ": " + e.getMessage());
        }
    }
}

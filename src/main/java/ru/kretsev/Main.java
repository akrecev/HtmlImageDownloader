package ru.kretsev;

import org.apache.commons.validator.routines.UrlValidator;
import ru.kretsev.Exception.HtmlDownloadException;
import ru.kretsev.Exception.HtmlParsingException;
import ru.kretsev.Exception.DownloadDirectoryException;
import ru.kretsev.service.HtmlDownloader;
import ru.kretsev.service.ImageDownloader;
import ru.kretsev.service.ImageParser;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UrlValidator urlValidator = new UrlValidator();
        String url;

        while (true) {
            System.out.print("Enter URL (for example, https://www.nasa.gov/) or type 'exit' to quit: ");
            url = scanner.nextLine().trim();

            if (url.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                return;
            }

            if (urlValidator.isValid(url)) {
                break;
            } else {
                System.err.println("The entered text is not a valid URL. Please try again.");
            }
        }

        System.out.print("Enter the folder path (for example, C:\\Images or Images for a folder inside the project): ");
        String outputDir = scanner.nextLine().trim();

        scanner.close();

        try {
            HtmlDownloader htmlDownloader = new HtmlDownloader();
            ImageParser imageParser = new ImageParser();
            ImageDownloader imageDownloader = new ImageDownloader();

            String html = htmlDownloader.downloadHtml(url);
            List<String> imageUrls = imageParser.parseImageUrls(html);
            imageDownloader.downloadImages(imageUrls, outputDir);

            System.out.println("Successfully loaded " + imageUrls.size() + " images");

        } catch (HtmlDownloadException | HtmlParsingException | DownloadDirectoryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
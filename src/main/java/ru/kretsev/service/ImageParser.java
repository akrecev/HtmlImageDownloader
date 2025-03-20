package ru.kretsev.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.kretsev.Exception.HtmlParsingException;

import java.util.ArrayList;
import java.util.List;

public class ImageParser {
    public List<String> parseImageUrls(String html) throws HtmlParsingException {
        List<String> imageUrls = new ArrayList<>();

        try {
            Document doc = Jsoup.parse(html);
            Elements imgTags = doc.select("img");

            for (Element img : imgTags) {
                String src = img.attr("src");
                if (!src.isEmpty()) {
                    imageUrls.add(src);
                }
            }
        } catch (Exception e) {
            throw new HtmlParsingException("Failed to parse HTML: " + html);
        }

        return imageUrls;
    }
}


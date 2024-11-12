package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        try {
            for (int pageNum = 1; pageNum <= 50; pageNum++) {
                String pageUrl = "https://www.eman.uz/en/product/66/list/?page=" + pageNum;
                Document doc = Jsoup.connect(pageUrl).get();

                Elements productCards = doc.select("div.products-card._second");

                for (Element card : productCards) {
                    String imgSrc = "https://www.eman.uz" + card.select("div.products-card-image img").attr("src");
                    String productLink = "https://www.eman.uz"
                            + card.select("div.products-card-info h3 a").attr("href");
                    String productName = card.select("div.products-card-info h3 a").text();

                    System.out.println("Image Source: " + imgSrc);
                    System.out.println("Product Link: " + productLink);
                    System.out.println("Product Name: " + productName);

                    Document productDoc = Jsoup.connect(productLink).get();
                    String productCode = productDoc.select("div.products-nav > span").text();

                    System.out.println(productCode);
                    System.out.println("------------------------------------");
                }

                Thread.sleep(1000); 
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

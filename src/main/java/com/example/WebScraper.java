// package com.example;

// import org.apache.poi.xwpf.usermodel.XWPFDocument;
// import org.apache.poi.xwpf.usermodel.XWPFParagraph;
// import org.apache.poi.xwpf.usermodel.XWPFRun;
// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;

// import java.io.FileOutputStream;
// import java.io.IOException;

// public class WebScraper {
//     public static void main(String[] args) {
//         try (XWPFDocument document = new XWPFDocument();
//              FileOutputStream out = new FileOutputStream("ScrapedDataMesan.docx")) {

//             for (int pageNum = 1; pageNum <= 50; pageNum++) {
//                 String pageUrl = "https://www.eman.uz/en/product/66/list/?page=" + pageNum;
//                 Document doc = Jsoup.connect(pageUrl).get();

//                 Elements productCards = doc.select("div.products-card._second");

//                 for (Element card : productCards) {
//                     String imgSrc = "https://www.eman.uz" + card.select("div.products-card-image img").attr("src");
//                     String productLink = "https://www.eman.uz" + card.select("div.products-card-info h3 a").attr("href");
//                     String productName = card.select("div.products-card-info h3 a").text();

//                     Document productDoc = Jsoup.connect(productLink).get();
//                     String productCode = productDoc.select("div.products-nav > span").text();

//                     XWPFParagraph paragraph = document.createParagraph();
//                     XWPFRun run = paragraph.createRun();
//                     run.setText("Image Source: " + imgSrc);
//                     run.addBreak();
//                     run.setText("Product Link: " + productLink);
//                     run.addBreak();
//                     run.setText("Product Name: " + productName);
//                     run.addBreak();
//                     run.setText("Product Code: " + productCode);
//                     run.addBreak();
//                     run.addBreak();

//                     System.out.println("Image Source: " + imgSrc);
//                     System.out.println("Product Link: " + productLink);
//                     System.out.println("Product Name: " + productName);
//                     System.out.println(productCode);
//                     System.out.println("------------------------------------");
//                 }

//                 Thread.sleep(1000); 
//             }

//             document.write(out); 
//             System.out.println("Data successfully written to ScrapedData.docx");

//         } catch (IOException | InterruptedException e) {
//             e.printStackTrace();
//         }
//     }
// }

package com.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream("ScrapedDataMesan.docx")) {

            for (int pageNum = 1; pageNum <= 10; pageNum++) {  // Limit pages to 10
                String pageUrl = "https://www.eman.uz/ru/product/128/list/?page=" + pageNum;
                Document doc = Jsoup.connect(pageUrl).get();

                Elements productCards = doc.select("div.products-card._second");

                for (Element card : productCards) {
                    String productLink = "https://www.eman.uz" + card.select("div.products-card-info h3 a").attr("href");
                    String productName = card.select("div.products-card-info h3 a").text();

                    Document productDoc = Jsoup.connect(productLink).get();
                    String productCode = productDoc.select("div.products-nav > span").text();

                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setText("Product Link: " + productLink);
                    run.addBreak();
                    run.setText("Product Name: " + productName);
                    run.addBreak();
                    run.setText(productCode);
                    run.addBreak();
                    run.addBreak();

                    System.out.println("Product Link: " + productLink);
                    System.out.println("Product Name: " + productName);
                    System.out.println(productCode);
                    System.out.println("------------------------------------");
                }

                Thread.sleep(1000); 
            }

            document.write(out); 
            System.out.println("Data successfully written to ScrapedDataMesan.docx");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

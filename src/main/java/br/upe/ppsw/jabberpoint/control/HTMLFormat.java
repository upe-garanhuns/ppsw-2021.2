package br.upe.ppsw.jabberpoint.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.upe.ppsw.jabberpoint.model.Presentation;
import br.upe.ppsw.jabberpoint.model.Slide;

public class HTMLFormat implements IFilePresentationFormat {

	public String getExtension() {
		
		return "html";
	}


	public Presentation load(String fileName) {
        Presentation presentation = new Presentation();

        try {
            Document document = Jsoup.parse(new File(fileName), "utf-8");
            presentation.setTitle(document.getElementsByTag("title").text());
            Integer countSlides = 0;
            for (Element item : document.getElementsByTag("div")) {
            	Slide slide = new Slide();
            	slide.setTitle(item.getElementsByClass("title" + countSlides).text());   
            	countSlides ++;
            	presentation.add(slide);
            }
            presentation.setCurrentSlideNumber(0);
            return presentation;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
	}

	
	public void save(Presentation presentation, String fileName) {
		File file = new File(fileName);
        
        try {
            BufferedWriter textHtml = new BufferedWriter(new FileWriter(file));
            textHtml.write("<html lang=\"pt-br\"> <body>");
            for (int i = 0; i < presentation.getSlidesSize(); i++) {
                textHtml.write("<div>");
                textHtml.write("<h1 class=\"title" + i + "\">" + presentation.getSlide(i).getTitle() + "</h1>");
                textHtml.write("</div>");
            }
            textHtml.write("</body></html>");
            textHtml.close();    
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}

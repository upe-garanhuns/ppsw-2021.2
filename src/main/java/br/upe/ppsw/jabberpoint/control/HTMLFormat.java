package br.upe.ppsw.jabberpoint.control;

import java.io.File;
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
            
            Integer countSlides = 0;
            for (Element item : document.getElementsByTag("slide")) {
            	Slide slide = new Slide();
            	slide.setTitle(document.getElementsByClass("title" + countSlides).text());   
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
		
		
	}

}

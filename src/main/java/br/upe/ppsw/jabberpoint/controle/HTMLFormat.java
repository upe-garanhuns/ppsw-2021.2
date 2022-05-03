package br.upe.ppsw.jabberpoint.controle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.Slide;
import br.upe.ppsw.jabberpoint.modelo.SlideItem;
import br.upe.ppsw.jabberpoint.modelo.SlideItemType;

public class HTMLFormat implements IFilePresentationFormat {

	@Override
	public Presentation load(String fileName) {
		Path path = Paths.get(fileName);
		Presentation presentation = new Presentation();

		try {
			Document document = Jsoup.parse(new File(fileName), "utf-8");
			Integer slidesSize = document.getElementsByTag("div").size();

			for (int i = 0; i < slidesSize; i++) {
				Slide slide = new Slide();
				String id = String.valueOf(i);

				for (Element item : document.getElementsByClass("slide" + id)) {
					slide.setTitle(document.getElementById("titulo" + id).text());
					slide.append(new SlideItem(2, document.getElementById("paragrafo" + id).text()));
				}

				presentation.add(slide);
			}

			presentation.setCurrentSlideNumber(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return presentation;
	}

	@Override
	public void save(Presentation presentation, String fileName) {
		File file = new File(fileName);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write("<html><body>");
			for (int i = 0; i < presentation.getSlidesSize(); i++) {
				bw.write("<div>");
				bw.write("<h1>" + presentation.getSlide(i).getTitle() + "</h1>");
				for (SlideItem paragrafo : presentation.getSlide(i).getItems()) {
					if (paragrafo.getType() == SlideItemType.TEXT) {
						bw.write("<p>" + paragrafo.getText() + "</p>");						
					}
				}
				bw.write("</div>");
			}
			bw.write("</body></html>");
			bw.close();	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

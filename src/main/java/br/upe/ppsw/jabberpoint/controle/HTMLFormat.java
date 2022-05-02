package br.upe.ppsw.jabberpoint.controle;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.Slide;
import br.upe.ppsw.jabberpoint.modelo.SlideItem;

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
		// TODO
	}
}
